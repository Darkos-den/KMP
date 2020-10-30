package com.darkos.kts.feature.signin.model.mvu

import com.darkos.mvu.models.Message

sealed class SignInMessage : Message() {
    class EmailChanged(val value: String) : SignInMessage()
    class PasswordChanged(val value: String) : SignInMessage()
    object SignInClick : SignInMessage()
}