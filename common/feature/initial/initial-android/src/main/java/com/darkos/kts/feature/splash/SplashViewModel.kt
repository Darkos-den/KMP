package com.darkos.kts.feature.splash

import com.darkos.core.presentation.ComponentViewModel
import com.darkos.kts.feature.splash.model.mvu.SplashState
import com.darkos.mvu_program.Program

class SplashViewModel(
    effectHandler: ISplashEffectHandler,
    reducer: ISplashReducer
) : ComponentViewModel<SplashState, ISplashEffectHandler, ISplashReducer>(
    effectHandler, reducer
) {

    override val initial = SplashState()
}