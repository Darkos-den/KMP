package com.company.projectName.auth

import com.company.projectName.auth.model.mvu.AuthScreenState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.StateCmdData

class AuthReducer: IAuthReducer {

    override fun update(state: AuthScreenState, message: Message): StateCmdData<AuthScreenState> {
        when(message){

        }
    }
}