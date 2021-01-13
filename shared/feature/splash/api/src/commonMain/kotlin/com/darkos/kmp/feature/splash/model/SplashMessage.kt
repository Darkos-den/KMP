package com.darkos.kmp.feature.splash.model

import com.darkos.mvu.model.Message

sealed class SplashMessage : Message() {
    object NetworkError : SplashMessage()
    class AppError(val message: String) : SplashMessage()
}