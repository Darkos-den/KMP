package com.darkos.kts.feature.signin.model.mvu

import com.darkos.mvu.models.Effect

sealed class SignInEffect : Effect() {
    sealed class Navigation : SignInEffect() {
        object NavigateToSignUp : Navigation()
    }
}