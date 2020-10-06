package com.company.projectName.login

import com.company.projectName.login.model.FieldValueChanged
import com.company.projectName.login.model.WithoutValidationField
import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.validation.ValidationReducer
import com.darkos.mvu.validation.model.Field
import com.darkos.mvu.validation.model.FieldValidationStatus
import com.darkos.mvu.validation.model.ValidationFieldType
import com.darkos.mvu.validation.model.mvu.ValidationMessage
import kotlin.reflect.KClass

class LoginReducer<State : MVUState, Request : Any> private constructor(
    private val loginEffectCreator: LoginEffectCreator<State, Request>,
    private val progressProcessor: ((State, Boolean) -> State)?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<State>>,
    private val validationMessages: List<ValidationChangeMessage>,
    validation: ValidationReducer<State>?
) : Reducer<State> {

    private val validationProcessor = ValidationProcessor(validation)

    override fun update(state: State, message: Message): StateCmdData<State> {
        validationMessages.forEach {
            if (it.message.isInstance(message)) {
                ValidationMessage.FieldValueChanged(
                    fieldId = it.fieldId,
                    newValue = (message as FieldValueChanged).newValue
                ).let {
                    validationProcessor.processValidationMessage(state, it)
                }
            }
        }

        return when (message) {
            is ValidationMessage -> validationProcessor.processValidationMessage(state, message)
            is LoginMessage -> processLoginMessage(state, message)
            else -> {
                processFieldChangeMessage(state, message)
                    ?: validationProcessor.processOtherMessage(state, message)
                    ?: throw IllegalArgumentException()
            }
        }
    }

    private fun processFieldChangeMessage(state: State, message: Message): StateCmdData<State>? {
        return withoutValidationProcessors.firstOrNull {
            it.valueChangedMessage.isInstance(message)
        }?.let {
            it.update(
                state = WithoutValidationField(""),
                message = message
            ).map { field ->
                it.mapTo(state, field)
            }
        }
    }

    private fun processProgressState(state: State, value: Boolean): State {
        return progressProcessor?.invoke(state, value) ?: state
    }

    inner class ValidationProcessor(
        private val validation: ValidationReducer<State>?
    ) {
        fun processValidationMessage(
            state: State,
            message: ValidationMessage
        ): StateCmdData<State> {
            return when (message) {
                is ValidationMessage.Success -> {
                    createProgressState(state)
                }
                is ValidationMessage.Error -> {
                    processValidationError(state, message)
                }
                else -> throw IllegalArgumentException()
            }
        }

        private fun processValidationError(
            state: State,
            message: ValidationMessage
        ): StateCmdData<State> {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = message
                ).map {
                    validation.mapper(state, it)
                }.map { newState ->
                    progressProcessor?.invoke(newState, false) ?: state
                }
            } ?: throw IllegalArgumentException()
        }

        fun processOtherMessage(
            state: State,
            message: Message
        ): StateCmdData<State>? {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = message
                ).map {
                    validation.mapper(state, it)
                }
            }
        }

        fun processValidationClick(state: State): StateCmdData<State>? {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = ValidationMessage.ValidationClick
                ).map {
                    processProgressState(validation.mapper(state, it), true)
                }
            }
        }
    }

    private fun createProgressState(state: State): StateCmdData<State> {
        return StateCmdData(
            state = processProgressState(state, true),
            effect = loginEffectCreator.create(state)
        )
    }

    private fun processLoginMessage(
        state: State,
        message: LoginMessage
    ): StateCmdData<State> {
        return when (message) {
            is LoginMessage.LoginClick -> {
                validationProcessor.processValidationClick(state) ?: run {
                    createProgressState(state)
                }
            }
            is LoginMessage.LoginSuccess -> {
                StateCmdData(
                    state = processProgressState(state, false),
                    effect = LoginEffect.Success
                )
            }
            is LoginMessage.LoginFailed -> {
                StateCmdData(
                    state = processProgressState(state, false),
                    effect = LoginEffect.Error(message.e)
                )
            }
            else -> throw IllegalArgumentException()
        }
    }

    data class ValidationState(
        val id: Long,
        val valid: Boolean
    )

    data class ValidationChangeMessage(
        val fieldId: Long,
        val message: KClass<out FieldValueChanged>
    )

    sealed class FieldType {
        object Email : FieldType()
        data class MinSymbols(val minCount: Int) : FieldType()
        data class MaxSymbols(val maxCount: Int) : FieldType()
        data class IntervalSymbols(val minCount: Int, val maxCount: Int) : FieldType()
        data class Custom(val id: Long) : FieldType()
    }

    class ValidationBuilder<State : MVUState> {
        private val builder: ValidationReducer.Builder<State> = ValidationReducer.Builder()
        private var messages: List<ValidationChangeMessage> = emptyList()

        fun RegisterValidationMapper(block: (State, List<ValidationState>) -> State) {
            builder.RegisterValidationMapper { state, validationState ->
                validationState.fields.map {
                    ValidationState(
                        id = it.id,
                        valid = it.status == FieldValidationStatus.VALID
                    )
                }.let {
                    block(state, it)
                }
            }
        }

        fun <Message : FieldValueChanged> registerField(
            fieldId: Long,
            fieldType: FieldType,
            messageClass: KClass<Message>,
            map: (State) -> String
        ) {
            builder.registerField(
                fieldId = fieldId,
                map = {
                    Field(
                        id = fieldId,
                        type = when (fieldType) {
                            is FieldType.Email -> ValidationFieldType.Email
                            is FieldType.Custom -> ValidationFieldType.Custom(fieldType.id)
                            is FieldType.IntervalSymbols -> ValidationFieldType.IntervalSymbols(
                                fieldType.minCount,
                                fieldType.maxCount
                            )
                            is FieldType.MinSymbols -> ValidationFieldType.MinSymbols(fieldType.minCount)
                            is FieldType.MaxSymbols -> ValidationFieldType.MaxSymbols(fieldType.maxCount)
                        },
                        value = map(it)
                    )
                }
            )

            messages = messages + ValidationChangeMessage(fieldId, messageClass)
        }

        fun build() = builder.build() to messages
    }

    class Builder<State : MVUState, Request : Any> {
        private var progressProcessor: ((State, Boolean) -> State)? = null
        private var withoutValidationProcessors: List<WithoutValidationReducer<State>> = emptyList()
        private var validation: ValidationReducer<State>? = null
        private var validationMessages: List<ValidationChangeMessage> = emptyList()
        private var loginEffectCreator: LoginEffectCreator<State, Request>? = null

        fun ProcessProgress(block: (State, Boolean) -> State) {
            progressProcessor = block
        }

        fun WithoutValidation(block: WithoutValidationReducer.Builder<State>.() -> Unit) {
            withoutValidationProcessors =
                WithoutValidationReducer.Builder<State>().apply(block).build()
        }

        fun WithValidation(block: ValidationBuilder<State>.() -> Unit) {
            ValidationBuilder<State>().apply(block).build().let {
                validation = it.first
                validationMessages = it.second
            }
        }

        fun MapStateToLoginRequest(block: (State) -> Request) {
            loginEffectCreator = LoginEffectCreator(block)
        }

        fun build() = LoginReducer(
            loginEffectCreator = loginEffectCreator!!,
            progressProcessor = progressProcessor,
            withoutValidationProcessors = withoutValidationProcessors,
            validation = validation
        )
    }
}



