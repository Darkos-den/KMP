package com.darkos.kts.auth.model.mvu

import com.darkos.mvu.models.Effect

sealed class AuthEffect : Effect() {
    sealed class Navigation : AuthEffect() {
        object NavigateToSignIn : Navigation()
    }
}