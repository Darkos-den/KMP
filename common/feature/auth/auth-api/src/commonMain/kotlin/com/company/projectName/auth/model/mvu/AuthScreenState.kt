package com.company.projectName.auth.model.mvu

import com.darkos.mvu.models.MVUState

data class AuthScreenState(
    val email: String,
    val password: String,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
): MVUState()