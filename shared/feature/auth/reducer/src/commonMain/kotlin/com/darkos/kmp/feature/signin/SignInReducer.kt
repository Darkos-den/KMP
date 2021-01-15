package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.kmp.common.errorHandler.processErrorMessage
import com.darkos.kmp.common.mvu.RestoreState
import com.darkos.kmp.common.mvu.andEffect
import com.darkos.kmp.common.mvu.restoreState
import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.*
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class SignInReducer(
    private val emailValidationMessage: String,
    private val passwordValidationMessage: String
) : ISignInReducer {

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
                        error = emailValidationMessage.takeIf { message.emailStatus.not() }
                    ),
                    password = state.password.copy(
                        error = passwordValidationMessage.takeIf { message.passwordStatus.not() }
                    ),
                    screenState = ScreenState.EDIT
                ).none()
            }
            is RestoreState<*> -> {
                restoreState(message)
            }
            is ErrorMessage -> {
                processErrorMessage(message) {
                    state.copy(screenState = ScreenState.SIGN_IN_ERROR)
                }
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}