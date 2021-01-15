package com.darkos.kmp.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String,
    val expire: Long
)