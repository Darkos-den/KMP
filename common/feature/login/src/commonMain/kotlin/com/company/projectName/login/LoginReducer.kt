package com.company.projectName.login

import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.Field
import com.company.projectName.validation.model.FieldValidationStatus
import com.company.projectName.validation.model.mvu.ValidationEffect
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.*
import kotlin.reflect.KClass

class LoginReducer<T : MVUState> private constructor(
    private val statusProcessor: StatusProcessor<T>?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<T>>,
    private val validation: ValidationReducer<T>?
) : Reducer<T> {

    override fun update(state: T, message: Message): StateCmdData<T> {
        withoutValidationProcessors.firstOrNull {
            it.valueChangedMessage.isInstance(message)
        }?.let { reducer ->
            reducer.update(
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
                        effect = LoginEffect.Login(state)
                    )
                }
            }
            is ValidationMessage.Success -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, true)
                    } ?: state,
                    effect = LoginEffect.Login(state)
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
                    effect = statusProcessor?.onFailed?.invoke() ?: None()
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

    internal class StatusProcessor<T : MVUState>(
        val onStateChanged: ((T, Boolean) -> T)?,
        val onSuccess: (() -> Effect)?,
        val onFailed: (() -> Effect)?
    )

    class StatusProcessorBuilder<T : MVUState> {
        private var onStateChanged: ((T, Boolean) -> T)? = null
        private var onSuccess: (() -> Effect)? = null
        private var onFailed: (() -> Effect)? = null

        fun OnStateChanged(block: (T, Boolean) -> T) {
            onStateChanged = block
        }

        fun OnSuccess(block: () -> Effect) {
            onSuccess = block
        }

        fun OnFailed(block: () -> Effect) {
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

    class Builder<T : MVUState> {
        private var statusProcessor: StatusProcessor<T>? = null
        private var withoutValidationProcessors: List<WithoutValidationReducer<T>> = emptyList()
        private var validation: ValidationReducer<T>? = null

        fun ProcessStatus(block: StatusProcessorBuilder<T>.() -> Unit) {
            statusProcessor = StatusProcessorBuilder<T>().apply(block).build()
        }

        fun WithoutValidation(block: WithoutValidationBuilder<T>.() -> Unit) {
            withoutValidationProcessors = WithoutValidationBuilder<T>().apply(block).build()
        }

        fun WithValidation(block: ValidationBuilder<T>.() -> Unit) {
            validation = ValidationBuilder<T>().apply(block).build()
        }

        fun build() = LoginReducer(
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

fun <T : MVUState> LoginFeature(block: LoginReducer.Builder<T>.() -> Unit) =
    LoginReducer.Builder<T>().apply(block).build()

fun <T : MVUState, U : MVUState> StateCmdData<T>.map(mapper: (T) -> U): StateCmdData<U> {
    return StateCmdData(
        state = mapper(state),
        effect = effect
    )
}