package com.company.projectName.domain.feature.login

import com.company.projectName.domain.feature.textField.TextFieldState
import com.company.projectName.login.model.EmailPassword
import com.company.projectName.login.model.LoginState
import com.darkos.mvu.models.MVUState

data class LoginScreenState(
    val vhod: EmailPassword,
    val emailError: String,
    val passwordError: String,
    val passwordVisible: Boolean
) : MVUState() {

    companion object {
        val Initial = LoginScreenState(
            vhod = EmailPassword(
                email = "",
                password = "",
                progress = false
            ),
            emailError = "",
            passwordError = "",
            passwordVisible = false
        )
    }
}