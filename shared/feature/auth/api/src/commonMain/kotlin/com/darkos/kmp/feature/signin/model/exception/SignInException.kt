package com.darkos.kmp.feature.signin.model.exception

sealed class SignInException : Exception() {
    object ConnectionError : SignInException()
    object InvalidAuthData : SignInException()
}