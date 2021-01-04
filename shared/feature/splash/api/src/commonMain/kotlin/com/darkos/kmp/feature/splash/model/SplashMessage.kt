package com.darkos.kmp.feature.splash.model

import com.darkos.mvu.model.Message

sealed class SplashMessage : Message() {
    class ServerError(val message: String) : SplashMessage()
    object ConnectionError : SplashMessage()
}