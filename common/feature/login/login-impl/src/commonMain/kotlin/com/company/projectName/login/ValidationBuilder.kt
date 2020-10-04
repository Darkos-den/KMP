package com.company.projectName.login

import com.company.projectName.login.model.FieldValueChanged
import com.company.projectName.login.model.ValidationState
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.validation.model.Field
import kotlin.reflect.KClass

class ValidationBuilder<T : MVUState> {
    private var processors: List<WithValidationReducer<T>> = emptyList()
    var errorEffect: Effect? = null
    private var mapper: ((T, ValidationState) -> T)? = null

    fun RegisterValidationMapper(block: (T, ValidationState) -> T) {
        mapper = block
    }

    fun registerField(
        fieldId: Long,
        valueChangedMessage: KClass<out FieldValueChanged>,
        map: (T) -> Field
    ) {
        processors = processors + WithValidationReducer(
            fieldId, valueChangedMessage, map
        )
    }

    internal fun build() = ValidationReducer(
        withValidationProcessors = processors,
        mapper = mapper!!,
        errorEffect = errorEffect
    )
}