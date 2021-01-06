package com.darkos.kmp.feature.splash.model

import com.darkos.mvu.model.MVUState
import com.darkos.mvu.model.Message

data class RestoreState<T : MVUState>(
    val state: T
) : Message()//todo: move to core

sealed class SplashMessage : Message() {
    object Plus : SplashMessage()
    object Next : SplashMessage()
}