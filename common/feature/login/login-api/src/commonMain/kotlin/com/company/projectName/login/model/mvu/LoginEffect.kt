package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Effect

sealed class LoginEffect : Effect() {
    class Login<T : Any>(val data: T) : LoginEffect()
}