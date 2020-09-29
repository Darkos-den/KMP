package com.company.projectName.validation

import com.company.projectName.validation.model.Field
import com.company.projectName.validation.model.FieldValidationStatus
import com.company.projectName.validation.model.mvu.ValidationEffect
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

abstract class ValidationEffectHandler : EffectHandler {

    abstract fun validate(field: Field): Boolean

    override suspend fun call(effect: Effect): Message {
        require(effect is ValidationEffect.Validate) {
            "supported only ValidationEffect.Validate"
        }

        return effect.fields.filter {
            validate(it).not()
        }.takeIf {
            it.isNotEmpty()
        }?.map {
            it.copy(status = FieldValidationStatus.INVALID)
        }?.let {
            ValidationMessage.Error(it)
        } ?: run {
            ValidationMessage.Success
        }
    }
}