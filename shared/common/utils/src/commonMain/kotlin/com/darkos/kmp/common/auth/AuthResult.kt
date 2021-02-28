package com.darkos.kmp.common.auth

sealed class AuthResult {
    object Canceled: AuthResult()
    object Success: AuthResult()
    class Error(val error: Exception): AuthResult()
}