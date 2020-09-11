package com.company.projectName.domain.feature.login

import com.company.projectName.domain.feature.textField.TextFieldState
import com.darkos.mvu.models.MVUState

data class PasswordState(
    val fieldState: TextFieldState,
    val visible: Boolean
)

sealed class LoginState : MVUState() {
    abstract val email: TextFieldState
    abstract val password: PasswordState

    data class Edit(
        override val email: TextFieldState,
        override val password: PasswordState
    ) : LoginState()

    data class Progress(
        override val email: TextFieldState,
        override val password: PasswordState
    ) : LoginState()

    companion object {
        val Initial = Edit(
            email = TextFieldState.Edit("email"),
            password = PasswordState(
                fieldState = TextFieldState.Edit("password"),
                visible = false
            )
        )
    }
}