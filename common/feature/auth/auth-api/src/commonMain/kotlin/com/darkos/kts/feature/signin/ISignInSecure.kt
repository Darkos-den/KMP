package com.darkos.kts.feature.signin

interface ISignInSecure {
    suspend fun saveToken(token: String)
}