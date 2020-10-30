package com.darkos.kts.feature.splash

interface ISplashSecure {
    suspend fun isActiveUserFound(): Boolean
}