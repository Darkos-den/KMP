package com.company.projectName.auth.model.mvu

import com.darkos.mvu.models.Message

sealed class AuthMessage : Message() {
    class EmailChanged(val value: String) : AuthMessage()
    class PasswordChanged(val value: String) : AuthMessage()
    object SignUpClick : AuthMessage()
}