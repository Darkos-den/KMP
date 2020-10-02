package com.company.projectName.validation.model.mvu

import com.company.projectName.validation.model.Field
import com.darkos.mvu.models.Message

sealed class ValidationMessage : Message() {
    class Error(val wrongFields: List<Field>) : ValidationMessage()
    object Success : ValidationMessage()
    object ValidationClick: ValidationMessage()
}