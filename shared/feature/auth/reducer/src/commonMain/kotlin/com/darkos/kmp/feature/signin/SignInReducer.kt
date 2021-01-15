package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.mvu.RestoreState
import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.*
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
                    screenState = ScreenState.PROGRESS
                ) andEffect SignInEffect.ProcessSignIn(
                    email = state.email.text,
                    password = state.password.text
                )
            }
            is SignInMessage.ValidationError -> {
                state.copy(
                    email = state.email.copy(
                        error = "Email error".takeIf { message.emailStatus.not() }
                    ),
                    password = state.password.copy(
                        error = "Password error".takeIf { message.passwordStatus.not() }
                    ),
                    screenState = ScreenState.EDIT
                ).none()
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
            is SignInMessage.SignInNetworkError -> {
                state.copy(
                    screenState = ScreenState.SIGN_IN_ERROR
                ) andEffect SignInEffect.ProcessNetworkError
            }
            is SignInMessage.SignInAppError -> {
                state.copy(
                    screenState = ScreenState.SIGN_IN_ERROR
                ) andEffect SignInEffect.ProcessAppError(message.message)
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}