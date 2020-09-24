package com.company.projectName.login

import com.company.projectName.login.model.LoginState
import com.company.projectName.login.model.copyProgress
import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import kotlin.reflect.KClass

class LoginReducer<T : LoginState> private constructor(
    private val messageProcessors: List<MessageProcessor<T>>,
    private val successEffectBuilder: ((T)->Effect)?,
    private val failedEffectBuilder: ((T, LoginMessage.LoginFailed)->Effect)?
) : Reducer<T> {

    class MessageProcessor<T : LoginState>(
        val messageClass: KClass<out Message>,
        val process: (T, Message) -> T
    )

    override fun update(state: T, message: Message): StateCmdData<T> {
        messageProcessors.forEach {
            if (it.messageClass.isInstance(message)) {
                return StateCmdData(
                    state = it.process(state, message),
                    effect = None()
                )
            }
        }
        if (message is LoginMessage) {
            return when (message) {
                is LoginMessage.LoginClick -> {
                    StateCmdData(
                        state = state.copyProgress(true),
                        effect = LoginEffect.Login(state)
                    )
                }
                is LoginMessage.LoginSuccess -> {
                    StateCmdData(
                        state = state.copyProgress(false),
                        effect = successEffectBuilder?.invoke(state) ?: None()
                    )
                }
                is LoginMessage.LoginFailed -> {
                    StateCmdData(
                        state = state.copyProgress(false),
                        effect = failedEffectBuilder?.invoke(state, message) ?: None()
                    )
                }
            }
        }
        throw IllegalArgumentException()
    }

    class Values<T : LoginState> {
        private var processors: List<MessageProcessor<T>> = emptyList()

        fun <M : Message> registerMessage(
            messageClass: KClass<M>,
            processor: (T, M) -> T
        ) {
            val process: (T, Message) -> T = { state, message ->
                processor(state, message as M)
            }

            processors = processors + MessageProcessor(messageClass, process)
        }

        fun build() = processors
    }

    class Builder<T : LoginState> {
        private var values: List<MessageProcessor<T>> = emptyList()
        private var successEffectBuilder: ((T)->Effect)? = null
        private var failedEffectBuilder: ((T, LoginMessage.LoginFailed)->Effect)? = null

        fun ValueChanes(block: Values<T>.() -> Unit) {
            values = Values<T>().apply(block).build()
        }

        fun LoginSuccessEffect(block: (T)->Effect){
            successEffectBuilder = block
        }

        fun LoginFailedEffect(block: (T, LoginMessage.LoginFailed)->Effect){
            failedEffectBuilder = block
        }

        fun Validation() {

        }

        fun build() = LoginReducer(values, successEffectBuilder, failedEffectBuilder)
    }
}

fun <T : LoginState> LoginFeature(block: LoginReducer.Builder<T>.() -> Unit) =
    LoginReducer.Builder<T>().apply(block).build()