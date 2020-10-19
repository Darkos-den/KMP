package com.company.projectName.signin.model.mvu

import com.darkos.mvu.login.model.FieldValueChanged

sealed class SignInMessage(value: String): FieldValueChanged(value) {
    class EmailChanged(
        value: String
    ) : SignInMessage(value)

    class PasswordChanged(
        value: String
    ) : SignInMessage(value)
}