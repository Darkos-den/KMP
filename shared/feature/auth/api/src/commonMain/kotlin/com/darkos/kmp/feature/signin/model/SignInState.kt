package com.darkos.kmp.feature.signin.model

import com.darkos.mvu.model.MVUState

data class FieldState(
    val text: String,
    val error: String?
)

data class SignInState(
    val email: FieldState,
    val password: FieldState,
    val progress: Boolean
) : MVUState()