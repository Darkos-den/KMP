package com.company.projectName.signin.model.mvu

import com.company.projectName.login.model.FieldValueChanged
import com.darkos.mvu.validation.model.mvu.ValidationMessage

sealed class LoginScreenMessage {
    class EmailChanged(
        value: String
    ) : ValidationMessage.FieldValueChanged(value)

    class PasswordChanged(
        value: String
    ) : FieldValueChanged(value)
}