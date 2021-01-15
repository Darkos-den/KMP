package com.darkos.kmp.feature.splash.api

interface ISplashSecure {
    suspend fun isAuthTokenValid(): Boolean
    suspend fun isRefreshTokenValid(): Boolean

    suspend fun getRefreshToken(): String

    suspend fun saveAuthToken(token: String, expire: Long)
    suspend fun saveRefreshToken(token: String, expire: Long)
}