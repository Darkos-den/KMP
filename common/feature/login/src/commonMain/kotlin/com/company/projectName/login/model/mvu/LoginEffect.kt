package com.company.projectName.login.model.mvu

import com.company.projectName.login.model.LoginState
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.MVUState

sealed class LoginEffect : Effect() {
    class Login<T : MVUState>(val data: T) : LoginEffect()
}