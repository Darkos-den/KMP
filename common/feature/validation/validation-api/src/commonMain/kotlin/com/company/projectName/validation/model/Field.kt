package com.company.projectName.validation.model

import com.darkos.mvu.models.MVUState

data class Field(
    val id: Long,
    val type: ValidationFieldType,
    val value: String,
    val status: FieldValidationStatus = FieldValidationStatus.VALID
): MVUState()

enum class FieldValidationStatus {
    VALID, INVALID
}