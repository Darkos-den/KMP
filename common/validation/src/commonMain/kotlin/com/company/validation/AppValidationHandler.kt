package com.company.validation

import com.company.projectName.signin.model.Constants
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
import com.darkos.mvu.validation.IValidationHandler
import com.darkos.mvu.validation.ValidationHandler
import com.darkos.mvu.validation.model.ValidationFieldType

internal val appValidationHandler = ValidationHandler {
    ValidateCustom {
        when(val type = it.type){
            is ValidationFieldType.Custom -> {
                when(type.id){
                    Constants.FIELD_TYPE_PASSWORD -> {
                        true
                    }
                    else -> throw IllegalArgumentException("not supported field type")
                }
            }
            else -> throw IllegalArgumentException("not valid field type")
        }
    }
}