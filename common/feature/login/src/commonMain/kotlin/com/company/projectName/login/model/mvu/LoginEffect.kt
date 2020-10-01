package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.MVUState

sealed class LoginEffect : Effect() {
    class Login<T : Any>(val data: T) : LoginEffect()
}