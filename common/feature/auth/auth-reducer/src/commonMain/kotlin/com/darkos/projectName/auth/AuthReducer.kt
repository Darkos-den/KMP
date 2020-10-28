package com.darkos.projectName.auth

import com.darkos.projectName.auth.model.mvu.AuthEffect
import com.darkos.projectName.auth.model.mvu.AuthState
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

class AuthReducer : IAuthReducer {

    override fun update(state: AuthState, message: Message): StateCmdData<AuthState> {
        return when (message) {
            is ComponentInitialized -> {
                StateCmdData(
                    state = state,
                    effect = AuthEffect.Navigation.NavigateToSignIn
                )
            }
            else -> StateCmdData(state, None())
        }
    }
}