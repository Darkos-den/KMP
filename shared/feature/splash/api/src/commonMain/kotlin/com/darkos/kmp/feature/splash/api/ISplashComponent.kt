package com.darkos.kmp.feature.splash.api

import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState

interface BaseComponent<T : MVUState> : ProgramComponent<T> {
    fun restore(state: T)//todo: move to core
}

interface ISplashComponent : BaseComponent<SplashState> {
    fun onPlusClicked()
    fun onNextClicked()
}