package com.company.projectName.login

import com.company.projectName.login.model.FieldValueChanged
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.validation.model.Field
import com.darkos.mvu.validation.model.FieldValidationStatus
import kotlin.reflect.KClass

class WithValidationReducer<T : MVUState>(
    val fieldId: Long,
    val valueChangedMessage: KClass<out FieldValueChanged>,
    val map: (T) -> Field
) : Reducer<Field> {
    override fun update(
        state: Field,
        message: Message
    ): StateCmdData<Field> {
        return StateCmdData(
            state = state.copy(
                value = (message as FieldValueChanged).newValue,
                status = FieldValidationStatus.VALID
            ),
            effect = None()
        )
    }
}