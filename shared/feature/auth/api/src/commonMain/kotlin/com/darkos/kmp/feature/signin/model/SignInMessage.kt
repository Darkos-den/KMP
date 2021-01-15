package com.darkos.kmp.feature.signin.model

import com.darkos.mvu.model.Message

sealed class SignInMessage : Message() {
    class EmailChanged(val value: String) : SignInMessage()
    class PasswordChanged(val value: String) : SignInMessage()
    object SubmitClicked : SignInMessage()

    class ValidationError(
        val emailStatus: Boolean,
        val passwordStatus: Boolean
    ) : SignInMessage()
}