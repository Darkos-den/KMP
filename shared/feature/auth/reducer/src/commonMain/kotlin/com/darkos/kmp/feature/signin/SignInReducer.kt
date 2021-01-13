package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.mvu.RestoreState
import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.FieldState
import com.darkos.kmp.feature.signin.model.SignInEffect
import com.darkos.kmp.feature.signin.model.SignInMessage
import com.darkos.kmp.feature.signin.model.SignInState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class SignInReducer : ISignInReducer {

    private infix fun <T : MVUState> T.andEffect(cmd: Effect) =
        StateCmdData(state = this, effect = cmd)//todo: move to core lib

    override fun update(state: SignInState, message: Message): StateCmdData<SignInState> {
        return when (message) {
            is Idle,
            is ComponentInitialized -> {
                state.none()
            }
            is SignInMessage.EmailChanged -> {
                state.copy(
                    email = FieldState(
                        text = message.value,
                        error = null
                    )
                ).none()
            }
            is SignInMessage.PasswordChanged -> {
                state.copy(
                    password = FieldState(
                        text = message.value,
                        error = null
                    )
                ).none()
            }
            is SignInMessage.SubmitClicked -> {
                state.copy(
                    progress = true
                ) andEffect SignInEffect.ProcessSignIn(
                    email = state.email.text,
                    password = state.password.text
                )
            }
            is RestoreState<*> -> {
                message.state.let {
                    if (it is SignInState) {
                        it.none()
                    } else {
                        throw UnsupportedOperationException()
                    }
                }
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}