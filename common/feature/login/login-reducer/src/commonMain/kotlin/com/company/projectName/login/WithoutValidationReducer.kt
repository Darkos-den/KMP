package com.company.projectName.login

import com.company.projectName.login.model.FieldValueChanged
import com.company.projectName.login.model.WithoutValidationField
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import kotlin.reflect.KClass

class WithoutValidationReducer<T : MVUState> private constructor(//todo: split to lib
    val valueChangedMessage: KClass<out FieldValueChanged>,
    val mapTo: (T, WithoutValidationField) -> T
) : Reducer<WithoutValidationField> {
    override fun update(
        state: WithoutValidationField,
        message: Message
    ): StateCmdData<WithoutValidationField> {
        return if (valueChangedMessage.isInstance(message)) {
            StateCmdData(
                state = state.copy(value = (message as FieldValueChanged).newValue),
                effect = None()
            )
        } else {
            throw IllegalArgumentException()
        }
    }

    class Builder<T : MVUState> {
        private var reducers: List<WithoutValidationReducer<T>> = emptyList()

        fun registerField(
            valueChangedMessage: KClass<out FieldValueChanged>,
            mapTo: (T, WithoutValidationField) -> T
        ) {
            reducers = reducers + WithoutValidationReducer(
                valueChangedMessage = valueChangedMessage,
                mapTo = mapTo
            )
        }

        internal fun build() = reducers
    }
}