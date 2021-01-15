package com.darkos.kmp.feature.signin.model.dto

data class SignInResultDto(
    val auth: TokenDto,
    val refresh: TokenDto
)