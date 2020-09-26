package com.company.projectName.validation.model

data class Field(
    val id: Long,
    val type: Int,
    val value: String,
    val status: FieldValidationStatus = FieldValidationStatus.VALID
)

enum class FieldValidationStatus {
    VALID, INVALID
}