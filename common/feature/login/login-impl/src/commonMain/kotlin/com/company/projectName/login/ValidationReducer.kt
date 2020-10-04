package com.company.projectName.login

import com.company.projectName.login.model.ValidationState
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.*
import com.darkos.mvu.validation.model.mvu.ValidationEffect
import com.darkos.mvu.validation.model.mvu.ValidationMessage

class ValidationReducer<T : MVUState>(
    private val withValidationProcessors: List<WithValidationReducer<T>>,
    val mapper: (T, ValidationState) -> T,
    private val errorEffect: Effect?
    ) : Reducer<ValidationState> {

        fun map(state: T): ValidationState {
            return ValidationState(
                fields = withValidationProcessors.map {
                    it.map(state)
                }
            )
        }

        override fun update(
            state: ValidationState,
            message: Message
        ): StateCmdData<ValidationState> {
            return when (message) {
                is ValidationMessage.ValidationClick -> {
                    StateCmdData(
                        state = state,
                        effect = ValidationEffect.Validate(state.fields)
                    )
                }
                is ValidationMessage.Error -> {
                    var fields = state.fields
                    message.wrongFields.forEach {
                        fields = fields.replaceById(it.id, it)
                    }

                    StateCmdData(
                        state = ValidationState(fields),
                        effect = errorEffect ?: None()
                    )
                }
                else -> {
                    withValidationProcessors.firstOrNull {
                        it.valueChangedMessage.isInstance(message)
                    }?.let { reducer ->
                        reducer.update(
                            state = state.fields.first { it.id == reducer.fieldId },
                            message = message
                        ).map {
                            state.copy(
                                fields = state.fields.replaceById(it.id, it)
                            )
                        }
                    } ?: throw IllegalArgumentException()
                }
            }
        }
    }