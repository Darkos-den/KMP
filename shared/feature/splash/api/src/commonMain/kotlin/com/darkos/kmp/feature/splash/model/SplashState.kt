package com.darkos.kmp.feature.splash.model

import com.darkos.mvu.model.MVUState

sealed class SplashState : MVUState() {
    object Progress : SplashState()
    object NetworkError : SplashState()
    object ServerError : SplashState()
}