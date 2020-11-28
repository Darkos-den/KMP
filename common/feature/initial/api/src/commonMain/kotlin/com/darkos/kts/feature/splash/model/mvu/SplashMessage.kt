package com.darkos.kts.feature.splash.model.mvu

import com.darkos.mvu.models.Message

sealed class SplashMessage: Message() {
    object UserFound: SplashMessage()
    object UserNotFound: SplashMessage()
}