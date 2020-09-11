package com.company.projectName.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SampleResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)