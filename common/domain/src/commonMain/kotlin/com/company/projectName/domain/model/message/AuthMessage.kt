package com.company.projectName.domain.model.message

import com.darkos.mvu.models.Message

sealed class AuthMessage : Message() {
    object LoginValidationSuccess : AuthMessage()
    class LoginValidationError(
        val emailError: String?,
        val passwordError: String?
    ) : AuthMessage()

    object LoginSuccess: AuthMessage()
    class LoginError(val message: String): AuthMessage()
}