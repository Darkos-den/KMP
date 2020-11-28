package com.darkos.kts.feature.auth

import com.darkos.kts.feature.signin.ISignInReducer
import com.darkos.kts.feature.signin.LoginByEmailReducer
import com.darkos.kts.feature.signin.model.mvu.*
import com.darkos.mvu.login.model.mvu.LoginMessage
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.validation.model.mvu.ValidationMessage

class SignInReducer : ISignInReducer {

    private val loginByEmailReducer = LoginByEmailReducer {
        enableEmailValidation()
        enablePasswordValidation()
    }

    private fun mapToAuthState(state: LoginByEmailState): SignInState {
        return SignInState(
            email = state.email,
            password = state.password,
            emailError = state.emailError,
            passwordError = state.passwordError,
            progress = state.progress
        )
    }

    private fun mapToSignInState(state: SignInState): LoginByEmailState {
        return LoginByEmailState(
            email = state.email,
            password = state.password,
            emailError = state.emailError,
            passwordError = state.passwordError,
            progress = state.progress
        )
    }

    override fun update(//todo: check memory leak in `Program`
        state: SignInState,
        message: Message
    ): StateCmdData<SignInState> {
        return when (val translated = translate(message)) {
            is LoginMessage,
            is ValidationMessage,
            is LoginByEmailMessage -> {
                loginByEmailReducer.update(
                    state = mapToSignInState(state),
                    message = translated
                ).map(this::mapToAuthState)
            }
            is SignInMessage.SignUpClick -> StateCmdData(
                state = state,
                effect = SignInEffect.Navigation.NavigateToSignUp
            )
            else -> StateCmdData(
                state = state,
                effect = None
            )
        }
    }

    private fun translate(message: Message): Message {
        return when (message) {
            is SignInMessage.EmailChanged -> LoginByEmailMessage.EmailChanged(
                message.value
            )
            is SignInMessage.PasswordChanged -> LoginByEmailMessage.PasswordChanged(
                message.value
            )
            is SignInMessage.SignInClick -> LoginMessage.LoginClick
            else -> message
        }
    }
}