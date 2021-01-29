package com.darkos.kmp.feature.signin.model

import com.darkos.mvu.model.Message

sealed class SignInMessage : Message() {
    class EmailChanged(val value: String) : SignInMessage()
    class PasswordChanged(val value: String) : SignInMessage()

    /**
     * переводит экран в состояние прогресса, порождает эффект [SignInEffect.ProcessSignIn]
     */
    object SubmitClicked : SignInMessage()

    class ValidationError(
        val emailStatus: Boolean,
        val passwordStatus: Boolean
    ) : SignInMessage()
}