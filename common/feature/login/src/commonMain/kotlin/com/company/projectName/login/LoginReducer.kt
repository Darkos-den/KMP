package com.company.projectName.login

import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.*
import kotlin.reflect.KClass

class LoginReducer<T : MVUState> private constructor(
    private val statusProcessor: StatusProcessor<T>?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<T>>
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
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, true)
                    } ?: state,
                    effect = LoginEffect.Login(state)
                )
            }
            is ValidationMessage.Success -> {
                throw IllegalArgumentException()
            }
            is ValidationMessage.Error -> {
                throw IllegalArgumentException()
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
                throw IllegalArgumentException()
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

    class WithoutValidationBuilder<T: MVUState> {
        private var reducers: List<WithoutValidationReducer<T>> = emptyList()

        fun registerField(
            valueChangedMessage: KClass<out FieldValueChanged>,
            mapTo: (T, WithoutValidationField) -> T
        ){
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

        fun ProcessStatus(block: StatusProcessorBuilder<T>.() -> Unit) {
            statusProcessor = StatusProcessorBuilder<T>().apply(block).build()
        }

        fun WithoutValidation(block: WithoutValidationBuilder<T>.() -> Unit) {
            withoutValidationProcessors = WithoutValidationBuilder<T>().apply(block).build()
        }

        fun WithValidation(block: () -> Unit) {

        }

        fun build() = LoginReducer(
            statusProcessor = statusProcessor,
            withoutValidationProcessors = withoutValidationProcessors
        )
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