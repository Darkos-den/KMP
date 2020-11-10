package com.darkos.kts.feature.splash.model

import com.darkos.mvu.navigator.Navigation

sealed class SplashNavigation : Navigation() {
    object NavigateToLogin : SplashNavigation()
    object NavigateToMain : SplashNavigation()
}