package com.darkos.kts.feature.splash

import com.darkos.core.presentation.ComponentViewModel
import com.darkos.kts.feature.splash.model.mvu.SplashState

class SplashViewModel(
    effectHandler: ISplashEffectHandler,
    reducer: ISplashReducer
) : ComponentViewModel<SplashState, ISplashEffectHandler, ISplashReducer>(
    effectHandler, reducer
) {

    override fun initial() = SplashState()
}