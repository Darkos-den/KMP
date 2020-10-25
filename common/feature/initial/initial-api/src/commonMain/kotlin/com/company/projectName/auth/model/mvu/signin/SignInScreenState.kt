package com.company.projectName.auth.model.mvu.signin

import com.darkos.mvu.models.MVUState

data class SignInScreenState(
    val email: String,
    val password: String,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
): MVUState()