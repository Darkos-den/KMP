package com.darkos.kts.auth.model.mvu.signin

import com.darkos.mvu.models.Message

sealed class SignInMessage : Message() {
    class EmailChanged(val value: String) : SignInMessage()
    class PasswordChanged(val value: String) : SignInMessage()
    object SignUpClick : SignInMessage()
}