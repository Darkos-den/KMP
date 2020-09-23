package com.company.projectName.domain.feature.login

import com.darkos.mvu.models.Message

sealed class LoginScreenMessage: Message() {
    class EmailChanged(val newValue: String): LoginScreenMessage()
    class PasswordChanged(val newValue: String): LoginScreenMessage()
    object PasswordVisibleClick: LoginScreenMessage()
    object SubmitClick: LoginScreenMessage()
}