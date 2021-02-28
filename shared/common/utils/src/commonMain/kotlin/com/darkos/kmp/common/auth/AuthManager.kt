package com.darkos.kmp.common.auth

interface AuthManager {
    suspend fun hasAuthorizedUser(): Boolean
    suspend fun signInByEmail(email: String, password: String): AuthResult
    suspend fun signOut()
}