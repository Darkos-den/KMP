package com.darkos.kmp

import com.darkos.kmp.feature.signin.api.ISignInNavigation
import com.darkos.kmp.feature.splash.api.ISplashNavigation

class CommonNavigator : ISplashNavigation, ISignInNavigation {
    var mGoToLogin: () -> Unit = {}
    var mGoToHome: () -> Unit = {}

    override fun fromSplashToLogin() {
        mGoToLogin()
    }

    override fun fromSplashToHome() {
        mGoToHome()
    }

    override fun fromSignInToHome() {
        mGoToHome()
    }
}