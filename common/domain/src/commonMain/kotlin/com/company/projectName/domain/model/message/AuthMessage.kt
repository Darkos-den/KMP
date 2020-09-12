package com.company.projectName.domain.model.message

import com.darkos.mvu.models.Message

sealed class AuthMessage : Message() {
    sealed class LoginValidation: AuthMessage(){
        object Success : LoginValidation()
        class Error(
            val emailError: String?,
            val passwordError: String?
        ) : LoginValidation()
    }

    sealed class Login: AuthMessage(){
        object Success: Login()
        class Error(val message: String): Login()
    }

    sealed class CheckUser: AuthMessage(){
        object Found: CheckUser()
        object NotFound: CheckUser()
    }
}