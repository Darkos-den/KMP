package com.darkos.kmp.feature.signin

import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.SignInState
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class SignInReducer : ISignInReducer {

    override fun update(state: SignInState, message: Message): StateCmdData<SignInState> {
        TODO("Not yet implemented")
    }
}