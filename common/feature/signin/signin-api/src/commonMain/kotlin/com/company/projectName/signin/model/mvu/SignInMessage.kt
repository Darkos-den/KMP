package com.company.projectName.signin.model.mvu

import com.company.projectName.login.model.FieldValueChanged

sealed class SignInMessage(value: String): FieldValueChanged(value) {
    class EmailChanged(
        value: String
    ) : SignInMessage(value)

    class PasswordChanged(
        value: String
    ) : SignInMessage(value)
}