package com.darkos.kmp.feature.signin

import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.FieldState
import com.darkos.kmp.feature.signin.model.SignInMessage
import com.darkos.kmp.feature.signin.model.SignInState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class SignInReducer : ISignInReducer {

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
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}