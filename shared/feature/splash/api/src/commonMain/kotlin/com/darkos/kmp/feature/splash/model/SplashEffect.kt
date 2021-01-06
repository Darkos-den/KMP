package com.darkos.kmp.feature.splash.model

import com.darkos.mvu.model.Effect

sealed class SplashEffect : Effect() {
    object CheckAuthState : SplashEffect()
    object RetryTokenRefresh : SplashEffect()
}