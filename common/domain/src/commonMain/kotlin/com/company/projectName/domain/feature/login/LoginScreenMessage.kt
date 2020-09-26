package com.company.projectName.domain.feature.login

import com.company.projectName.login.LoginReducer
import com.darkos.mvu.models.Message

sealed class LoginScreenMessage : Message() {
    class EmailChanged(
        value: String
    ) : LoginReducer.FieldValueChanged(value)

    class PasswordChanged(
        value: String
    ) : LoginReducer.FieldValueChanged(value)
}