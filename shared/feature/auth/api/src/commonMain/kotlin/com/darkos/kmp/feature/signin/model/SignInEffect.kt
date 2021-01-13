package com.darkos.kmp.feature.signin.model

import com.darkos.mvu.model.Effect

sealed class SignInEffect : Effect() {

    class ProcessSignIn(
        val email: String,
        val password: String
    ) : SignInEffect()
}