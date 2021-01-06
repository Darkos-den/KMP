package com.darkos.kmp.feature.splash.api

import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.ProgramComponent

interface ISplashComponent : ProgramComponent<SplashState> {
    fun onPlusClicked()
    fun onNextClicked()

    fun restore(state: SplashState)
}