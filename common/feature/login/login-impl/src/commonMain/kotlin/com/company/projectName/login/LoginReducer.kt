package com.company.projectName.login

import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.Field
import com.company.projectName.validation.model.FieldValidationStatus
import com.company.projectName.validation.model.mvu.ValidationEffect
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.*
import kotlin.reflect.KClass

class LoginReducer<State : MVUState, Request : Any> private constructor(
    private val loginEffectCreator: LoginEffectCreator<State, Request>,
    private val statusProcessor: StatusProcessor<State>?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<State>>,
    private val validation: ValidationReducer<State>?
) : Reducer<State> {

    override fun update(state: State, message: Message): StateCmdData<State> {
        withoutValidationProcessors.firstOrNull {
            it.valueChangedMessage.isInstance(message)
        }?.let { reducer ->
            return reducer.update(
                state = WithoutValidationField(""),
                message = message
            ).map {
                reducer.mapTo(state, it)
            }
        }

        return when (message) {
            is LoginMessage.LoginClick -> {
                validation?.let { validation ->
                    validation.update(
                        state = validation.map(state),
                        message = ValidationMessage.ValidationClick
                    ).map {
                        validation.mapper(state, it).let {
                            statusProcessor?.onStateChanged?.let {
                                it(state, true)
                            } ?: state
                        }
                    }
                } ?: run {
                    StateCmdData(
                        state = statusProcessor?.onStateChanged?.let {
                            it(state, true)
                        } ?: state,
                        effect = loginEffectCreator.create(state)
                    )
                }
            }
            is ValidationMessage.Success -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, true)
                    } ?: state,
                    effect = loginEffectCreator.create(state)
                )
            }
            is ValidationMessage.Error -> {
                validation?.let { validation ->
                    validation.update(
                        state = validation.map(state),
                        message = message
                    ).map {
                        validation.mapper(state, it)
                    }.map { newState ->
                        statusProcessor?.onStateChanged?.let {
                            it(newState, false)
                        } ?: state
                    }
                } ?: throw IllegalArgumentException()
            }
            is LoginMessage.LoginSuccess -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, false)
                    } ?: state,
                    effect = statusProcessor?.onSuccess?.invoke() ?: None()
                )
            }
            is LoginMessage.LoginFailed -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, false)
                    } ?: state,
                    effect = statusProcessor?.onFailed?.invoke(message.e) ?: None()
                )
            }
            else -> {
                validation?.let { validation ->
                    validation.update(
                        state = validation.map(state),
                        message = message
                    ).map {
                        validation.mapper(state, it)
                    }
                } ?: throw IllegalArgumentException()
            }
        }
    }

    class LoginEffectCreator<State : MVUState, Request : Any>(
        map: (State) -> Request
    ) {
        val create: (State) -> LoginEffect.Login<Request> = {
            map(it).let {
                LoginEffect.Login(it)
            }
        }
    }

    internal class StatusProcessor<T : MVUState>(
        val onStateChanged: ((T, Boolean) -> T)?,
        val onSuccess: (() -> Effect)?,
        val onFailed: ((Exception) -> Effect)?
    )

    class StatusProcessorBuilder<T : MVUState> {
        private var onStateChanged: ((T, Boolean) -> T)? = null
        private var onSuccess: (() -> Effect)? = null
        private var onFailed: ((Exception) -> Effect)? = null

        fun OnStateChanged(block: (T, Boolean) -> T) {
            onStateChanged = block
        }

        fun OnSuccess(block: () -> Effect) {
            onSuccess = block
        }

        fun OnFailed(block: (Exception) -> Effect) {
            onFailed = block
        }

        internal fun build(): StatusProcessor<T>? = StatusProcessor(
            onStateChanged,
            onSuccess,
            onFailed
        )
    }

    abstract class FieldValueChanged(
        val newValue: String
    ) : Message()

    data class WithoutValidationField(
        val value: String
    ) : MVUState()

    data class ValidationState(
        val fields: List<Field>
    ) : MVUState()

    class ValidationReducer<T : MVUState>(
        private val withValidationProcessors: List<WithValidationReducer<T>>,
        val mapper: (T, ValidationState) -> T,
        private val errorEffect: Effect?
    ) : Reducer<ValidationState> {

        fun map(state: T): ValidationState {
            return ValidationState(
                fields = withValidationProcessors.map {
                    it.map(state)
                }
            )
        }

        override fun update(
            state: ValidationState,
            message: Message
        ): StateCmdData<ValidationState> {
            return when (message) {
                is ValidationMessage.ValidationClick -> {
                    StateCmdData(
                        state = state,
                        effect = ValidationEffect.Validate(state.fields)
                    )
                }
                is ValidationMessage.Error -> {
                    var fields = state.fields
                    message.wrongFields.forEach {
                        fields = fields.replaceById(it.id, it)
                    }

                    StateCmdData(
                        state = ValidationState(fields),
                        effect = errorEffect ?: None()
                    )
                }
                else -> {
                    withValidationProcessors.firstOrNull {
                        it.valueChangedMessage.isInstance(message)
                    }?.let { reducer ->
                        reducer.update(
                            state = state.fields.first { it.id == reducer.fieldId },
                            message = message
                        ).map {
                            state.copy(
                                fields = state.fields.replaceById(it.id, it)
                            )
                        }
                    } ?: throw IllegalArgumentException()
                }
            }
        }
    }

    class ValidationBuilder<T : MVUState> {
        private var processors: List<WithValidationReducer<T>> = emptyList()
        var errorEffect: Effect? = null
        private var mapper: ((T, ValidationState) -> T)? = null

        fun RegisterValidationMapper(block: (T, ValidationState) -> T) {
            mapper = block
        }

        fun registerField(
            fieldId: Long,
            valueChangedMessage: KClass<out FieldValueChanged>,
            map: (T) -> Field
        ) {
            processors = processors + WithValidationReducer(
                fieldId, valueChangedMessage, map
            )
        }

        internal fun build() = ValidationReducer(
            withValidationProcessors = processors,
            mapper = mapper!!,
            errorEffect = errorEffect
        )
    }

    class WithoutValidationReducer<T : MVUState>(
        val valueChangedMessage: KClass<out FieldValueChanged>,
        val mapTo: (T, WithoutValidationField) -> T
    ) : Reducer<WithoutValidationField> {
        override fun update(
            state: WithoutValidationField,
            message: Message
        ): StateCmdData<WithoutValidationField> {
            return if (valueChangedMessage.isInstance(message)) {
                StateCmdData(
                    state = state.copy(value = (message as FieldValueChanged).newValue),
                    effect = None()
                )
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    class WithValidationReducer<T : MVUState>(
        val fieldId: Long,
        val valueChangedMessage: KClass<out FieldValueChanged>,
        val map: (T) -> Field
    ) : Reducer<Field> {
        override fun update(
            state: Field,
            message: Message
        ): StateCmdData<Field> {
            return StateCmdData(
                state = state.copy(
                    value = (message as FieldValueChanged).newValue,
                    status = FieldValidationStatus.VALID
                ),
                effect = None()
            )
        }
    }

    class WithoutValidationBuilder<T : MVUState> {
        private var reducers: List<WithoutValidationReducer<T>> = emptyList()

        fun registerField(
            valueChangedMessage: KClass<out FieldValueChanged>,
            mapTo: (T, WithoutValidationField) -> T
        ) {
            reducers = reducers + WithoutValidationReducer(
                valueChangedMessage = valueChangedMessage,
                mapTo = mapTo
            )
        }

        internal fun build() = reducers
    }

    class Builder<State : MVUState, Request : Any> {
        private var statusProcessor: StatusProcessor<State>? = null
        private var withoutValidationProcessors: List<WithoutValidationReducer<State>> = emptyList()
        private var validation: ValidationReducer<State>? = null
        private var loginEffectCreator: LoginEffectCreator<State, Request>? = null

        fun ProcessStatus(block: StatusProcessorBuilder<State>.() -> Unit) {
            statusProcessor = StatusProcessorBuilder<State>().apply(block).build()
        }

        fun WithoutValidation(block: WithoutValidationBuilder<State>.() -> Unit) {
            withoutValidationProcessors = WithoutValidationBuilder<State>().apply(block).build()
        }

        fun WithValidation(block: ValidationBuilder<State>.() -> Unit) {
            validation = ValidationBuilder<State>().apply(block).build()
        }

        fun MapStateToLoginRequest(block: (State) -> Request) {
            loginEffectCreator = LoginEffectCreator(block)
        }

        fun build() = LoginReducer(
            loginEffectCreator = loginEffectCreator!!,
            statusProcessor = statusProcessor,
            withoutValidationProcessors = withoutValidationProcessors,
            validation = validation
        )
    }
}

fun List<Field>.replaceById(id: Long, newValue: Field): List<Field> {
    return this.map {
        if (it.id == id) {
            newValue
        } else {
            it
        }
    }
}

fun <State : MVUState, Request : Any> LoginFeature(block: LoginReducer.Builder<State, Request>.() -> Unit) =
    LoginReducer.Builder<State, Request>().apply(block).build()