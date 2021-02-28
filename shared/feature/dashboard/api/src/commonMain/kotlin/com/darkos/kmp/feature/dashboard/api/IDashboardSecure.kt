package com.darkos.kmp.feature.dashboard.api

interface IDashboardSecure {
    suspend fun isAuthTokenValid(): Boolean
    suspend fun isRefreshTokenValid(): Boolean

    suspend fun getRefreshToken(): String

    suspend fun saveAuthToken(token: String, expire: Long)
    suspend fun saveRefreshToken(token: String, expire: Long)
}