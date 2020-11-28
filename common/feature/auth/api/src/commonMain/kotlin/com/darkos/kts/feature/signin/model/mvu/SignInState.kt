package com.darkos.kts.feature.signin.model.mvu

import com.darkos.mvu.models.MVUState

data class SignInState(
    val email: String,
    val password: String,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
): MVUState()