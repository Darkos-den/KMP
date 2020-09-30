package com.company.projectName.domain.effectHandler

import com.company.projectName.validation.ValidationEffectHandler
import com.company.projectName.validation.model.Field
import kotlinx.coroutines.delay

class ValidateEffectHandler: ValidationEffectHandler() {
    override suspend fun validate(field: Field): Boolean {
        delay(2000)
        return when (field.type) {
            FIELD_TYPE_EMAIL -> false
            FIELD_TYPE_PASSWORD -> false
            else -> false
        }
    }

    companion object{
        const val FIELD_TYPE_EMAIL = 0
        const val FIELD_TYPE_PASSWORD = 1
    }
}