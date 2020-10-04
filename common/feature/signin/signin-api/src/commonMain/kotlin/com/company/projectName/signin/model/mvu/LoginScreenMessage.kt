package com.company.projectName.signin.model.mvu

import com.company.projectName.login.model.FieldValueChanged
import com.darkos.mvu.models.Message

sealed class LoginScreenMessage : Message() {
    class EmailChanged(
        value: String
    ) : FieldValueChanged(value)

    class PasswordChanged(
        value: String
    ) : FieldValueChanged(value)
}