package com.darkos.kts.feature.splash.model.mvu

import com.darkos.mvu.models.Effect

sealed class SplashEffect: Effect() {

    sealed class Navigation : SplashEffect() {
        object NavigateToMain : Navigation()
        object NavigateToLogin : Navigation()
    }

    object CheckActiveUser: SplashEffect()
}