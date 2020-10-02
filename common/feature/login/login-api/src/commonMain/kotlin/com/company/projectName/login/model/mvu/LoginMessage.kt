package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Message

sealed class LoginMessage : Message() {
    object LoginClick : LoginMessage()
    object LoginSuccess: LoginMessage()
    class LoginFailed(val e: Exception): LoginMessage()
}