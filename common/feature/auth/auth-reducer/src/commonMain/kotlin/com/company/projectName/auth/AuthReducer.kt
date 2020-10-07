package com.company.projectName.auth

import com.company.projectName.auth.model.mvu.AuthMessage
import com.company.projectName.auth.model.mvu.AuthScreenState
import com.company.projectName.signin.model.mvu.SignInMessage
import com.company.projectName.signin.model.mvu.SignInScreenState
import com.company.projectName.signin.signInReducer
import com.darkos.mvu.map
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

class AuthReducer : IAuthReducer {

    private fun mapToAuthState(state: SignInScreenState): AuthScreenState {
        return AuthScreenState(
            email = state.email,
            password = state.password,
            emailError = state.emailError,
            passwordError = state.passwordError,
            progress = state.progress
        )
    }

    private fun mapToSignInState(state: AuthScreenState): SignInScreenState {
        return SignInScreenState(
            email = state.email,
            password = state.password,
            emailError = state.emailError,
            passwordError = state.passwordError,
            progress = state.progress
        )
    }

    override fun update(state: AuthScreenState, message: Message): StateCmdData<AuthScreenState> {
        return when (val translated = translate(message)) {
            is SignInMessage -> {
                signInReducer.update(
                    state = mapToSignInState(state),
                    message = translated
                ).map(this::mapToAuthState)
            }
            is AuthMessage.SignUpClick -> {
                TODO("navigate to sign up")
            }
            else -> StateCmdData(
                state = state,
                effect = None()
            )
        }
    }

    private fun translate(message: Message): Message {
        return when (message) {
            is AuthMessage.EmailChanged -> SignInMessage.EmailChanged(message.value)
            is AuthMessage.PasswordChanged -> SignInMessage.PasswordChanged(message.value)
            else -> message
        }
    }
}