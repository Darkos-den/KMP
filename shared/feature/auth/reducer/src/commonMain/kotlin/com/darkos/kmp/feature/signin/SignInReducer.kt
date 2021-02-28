package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.kmp.common.errorHandler.processErrorMessage
import com.darkos.kmp.common.mvu.restoreStateAndEffect
import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.*
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

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
                    ),
                    screenState = ScreenState.EDIT
                ).none()
            }
            is SignInMessage.PasswordChanged -> {
                state.copy(
                    password = FieldState(
                        text = message.value,
                        error = null
                    ),
                    screenState = ScreenState.EDIT
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
                restoreStateAndEffect(message) {
                    it.copy(screenState = ScreenState.PROGRESS) andEffect when (it.screenState) {
                        ScreenState.SIGN_IN_ERROR -> {
                            SignInEffect.ProcessSignIn(
                                email = it.email.text,
                                password = it.password.text
                            )
                        }
                        else -> {
                            None
                        }
                    }
                }
            }
            is ErrorMessage -> {
                processErrorMessage(message) {
                    state.copy(screenState = ScreenState.SIGN_IN_ERROR)
                }
            }
            is SignInMessage.SignInCanceled -> {
                state.copy(screenState = ScreenState.EDIT).none()
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}