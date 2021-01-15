package com.darkos.kmp.feature.signin.api

interface ISignInSecure {
    suspend fun saveAuthToken(token: String, expire: Long)
    suspend fun saveRefreshToken(token: String, expire: Long)
}