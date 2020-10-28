package com.darkos.kts.feature.initial.model.mvu.splash

import com.darkos.mvu.models.Effect

sealed class SplashEffect: Effect() {

    sealed class Navigation : SplashEffect() {
        object NavigateToMain : Navigation()
        object NavigateToLogin : Navigation()
    }

    class Delay(val millis: Long): SplashEffect()
}