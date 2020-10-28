package com.darkos.kts.initial.model.mvu.splash

import com.darkos.mvu.models.Message

sealed class SplashMessage: Message() {
    object DelayFinished: SplashMessage()
}