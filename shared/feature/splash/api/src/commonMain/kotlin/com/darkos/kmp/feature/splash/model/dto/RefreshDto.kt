package com.darkos.kmp.feature.splash.model.dto

data class RefreshDto(
    val auth: TokenDto,
    val refresh: TokenDto
)