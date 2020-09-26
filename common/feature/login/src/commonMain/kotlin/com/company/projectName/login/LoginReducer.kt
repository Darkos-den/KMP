package com.company.projectName.login

import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.*
import kotlin.reflect.KClass

class LoginReducer<T : MVUState> private constructor(
    private val statusProcessor: StatusProcessor<T>?
) : Reducer<T> {

    override fun update(state: T, message: Message): StateCmdData<T> {
        if (message is LoginMessage) {
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
            }
        }
        throw IllegalArgumentException()
    }

    internal class StatusProcessor<T : MVUState>(
        val onStateChanged: ((T, Boolean) -> T)?,
        val onSuccess: (() -> Effect)?,
        val onFailed: (() -> Effect)?
    )

    class WithoutValidationField(
        val valueChangedMessage: KClass<out Message>,
        val value: String
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

    class Builder<T : MVUState> {
        private var statusProcessor: StatusProcessor<T>? = null

        fun ProcessStatus(block: StatusProcessorBuilder<T>.() -> Unit) {
            statusProcessor = StatusProcessorBuilder<T>().apply(block).build()
        }

        fun WithoutValidation(block: () -> Unit) {

        }

        fun WithValidation(block: () -> Unit) {

        }

        fun build() = LoginReducer(
            statusProcessor = statusProcessor
        )
    }
}

fun <T : MVUState> LoginFeature(block: LoginReducer.Builder<T>.() -> Unit) =
    LoginReducer.Builder<T>().apply(block).build()