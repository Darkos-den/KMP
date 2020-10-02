package com.company.projectName.validation.model.mvu

import com.company.projectName.validation.model.Field
import com.darkos.mvu.models.Effect

sealed class ValidationEffect : Effect() {
    class Validate(val fields: List<Field>) : ValidationEffect()
}