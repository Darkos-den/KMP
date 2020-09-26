package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Message

sealed class LoginMessage : Message() {
    object LoginClick : LoginMessage()
    internal object LoginSuccess: LoginMessage()
    internal class LoginFailed(val e: Exception): LoginMessage()
}