package com.darkos.kmp.feature.signin.model

import com.darkos.mvu.model.MVUState

data class FieldState(
    val text: String,
    val error: String?
)

enum class ScreenState {
    EDIT, PROGRESS, SIGN_IN_ERROR
}

data class SignInState(
    val email: FieldState,
    val password: FieldState,
    val screenState: ScreenState
) : MVUState()