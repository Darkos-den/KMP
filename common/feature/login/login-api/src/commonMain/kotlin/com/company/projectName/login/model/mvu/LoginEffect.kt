package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Effect

sealed class LoginEffect : Effect() {
    class Process<T : Any>(val data: T) : LoginEffect()
    object Success: LoginEffect()
    class Error(val e: Exception): LoginEffect()
}