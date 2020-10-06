package com.company.projectName.signin.model.mvu

import com.company.projectName.login.model.FieldValueChanged

sealed class LoginScreenMessage(value: String): FieldValueChanged(value) {
    class EmailChanged(
        value: String
    ) : LoginScreenMessage(value)

    class PasswordChanged(
        value: String
    ) : LoginScreenMessage(value)
}