package com.company.projectName.signin.model.mvu

import com.darkos.mvu.models.MVUState

data class LoginScreenState(
    val email: String,
    val password: String,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
) : MVUState()