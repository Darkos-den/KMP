package com.darkos.kts.feature.signin.model.mvu

import com.darkos.mvu.login.model.FieldValueChanged

sealed class LoginByEmailMessage(value: String): FieldValueChanged(value) {
    class EmailChanged(
        value: String
    ) : LoginByEmailMessage(value)

    class PasswordChanged(
        value: String
    ) : LoginByEmailMessage(value)
}