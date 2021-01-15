package com.darkos.kmp.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)