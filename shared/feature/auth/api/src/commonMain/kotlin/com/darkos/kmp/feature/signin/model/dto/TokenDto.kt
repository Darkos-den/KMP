package com.darkos.kmp.feature.signin.model.dto

data class TokenDto(
    val token: String,
    val expire: Long
)