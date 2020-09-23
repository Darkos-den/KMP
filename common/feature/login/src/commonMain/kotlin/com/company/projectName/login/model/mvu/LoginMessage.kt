package com.company.projectName.login.model.mvu

import com.darkos.mvu.models.Message

sealed class LoginMessage : Message() {
    class Error(val e: Exception) : LoginMessage()
    object Success: LoginMessage()
}