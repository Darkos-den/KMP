package com.darkos.validation

import com.darkos.kts.feature.signin.model.Constants
import com.darkos.mvu.validation.ValidationHandler
import com.darkos.mvu.validation.model.ValidationFieldType

private const val MIN_PASS_LENGTH = 5

internal val appValidationHandler = ValidationHandler {
    ValidateCustom {
        when(val type = it.type){
            is ValidationFieldType.Custom -> {
                when(type.id){
                    Constants.FIELD_TYPE_PASSWORD -> {
                        it.value.length >= MIN_PASS_LENGTH
                    }
                    else -> throw IllegalArgumentException("not supported field type")
                }
            }
            else -> throw IllegalArgumentException("not valid field type")
        }
    }
}