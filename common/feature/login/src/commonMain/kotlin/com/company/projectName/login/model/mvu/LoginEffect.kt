package com.company.projectName.login.model.mvu

import com.company.projectName.login.model.LoginState
import com.darkos.mvu.models.Effect

sealed class LoginEffect : Effect() {
    class Login<T : LoginState>(val data: T) : LoginEffect()
}