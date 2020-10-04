package com.company.projectName.login

import com.company.projectName.login.model.mvu.LoginEffect
import com.darkos.mvu.models.MVUState

internal class LoginEffectCreator<State : MVUState, Request : Any>(
    map: (State) -> Request
) {
    val create: (State) -> LoginEffect.Login<Request> = {
        map(it).let {
            LoginEffect.Login(it)
        }
    }
}