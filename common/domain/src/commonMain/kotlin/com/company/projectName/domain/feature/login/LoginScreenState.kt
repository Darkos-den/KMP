package com.company.projectName.domain.feature.login

import com.company.projectName.domain.feature.textField.TextFieldState
import com.company.projectName.login.model.LoginState
import com.darkos.mvu.models.MVUState

data class VhodState(
    val email: String,
    val password: String,
    override val progress: Boolean
) : LoginState(){
    override fun copyWithProgress(newProgress: Boolean): LoginState {
        return this.copy(progress = newProgress)
    }
}

data class LoginScreenState(
    val vhod: VhodState,
    val emailError: String,
    val passwordError: String,
    val passwordVisible: Boolean
) : MVUState() {

    companion object {
        val Initial = LoginScreenState(
            vhod = VhodState(
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