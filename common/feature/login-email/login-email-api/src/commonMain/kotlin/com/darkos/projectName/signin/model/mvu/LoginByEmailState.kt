package com.darkos.projectName.signin.model.mvu

import com.darkos.mvu.models.MVUState

data class LoginByEmailState(
    val email: String,
    val password: String,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
) : MVUState()