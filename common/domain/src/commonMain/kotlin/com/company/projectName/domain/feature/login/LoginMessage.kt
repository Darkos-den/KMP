package com.company.projectName.domain.feature.login

import com.darkos.mvu.models.Message

sealed class LoginMessage: Message() {
    class EmailChanged(val newValue: String): LoginMessage()
    class PasswordChanged(val newValue: String): LoginMessage()
    object PasswordVisibleClick: LoginMessage()
    object SubmitClick: LoginMessage()
}