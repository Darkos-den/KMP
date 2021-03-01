package com.darkos.kmp.feature.drawer.model

import com.darkos.mvu.model.MVUState

sealed class SplashState : MVUState() {
    object Init : SplashState()
    object PrepareData : SplashState()
    object RefreshTokenError : SplashState()
}