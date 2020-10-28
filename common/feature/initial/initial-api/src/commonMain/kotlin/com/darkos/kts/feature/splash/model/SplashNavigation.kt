package com.darkos.kts.feature.splash.model

import com.darkos.core.model.navigation.Navigation

sealed class SplashNavigation : Navigation() {
    object NavigateToLogin : SplashNavigation()
    object NavigateToMain : SplashNavigation()
}