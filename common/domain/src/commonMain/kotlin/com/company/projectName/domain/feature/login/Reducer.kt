package com.company.projectName.domain.feature.login

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.effectHandler.ValidateEffectHandler.Companion.FIELD_TYPE_EMAIL
import com.company.projectName.domain.effectHandler.ValidateEffectHandler.Companion.FIELD_TYPE_PASSWORD
import com.company.projectName.domain.feature.textField.processError
import com.company.projectName.domain.feature.textField.processTextFieldValueChanged
import com.company.projectName.entity.models.dto.LoginDTO
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.Field
import com.company.projectName.validation.model.mvu.ValidationEffect
import com.company.projectName.validation.model.mvu.ValidationMessage
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

private const val FIELD_ID_EMAIL: Long = 1
private const val FIELD_ID_PASSWORD: Long = 2

val loginReducer = reducer<LoginState> { state, message ->
    when (message) {
        is LoginScreenMessage.EmailChanged -> {
            processEmailChanged(state, message)
        }
        is LoginScreenMessage.PasswordChanged -> {
            processPasswordChanged(state, message)
        }
        is LoginScreenMessage.PasswordVisibleClick -> {
            processPasswordVisibleClick(state)
        }
        is LoginScreenMessage.SubmitClick -> {
            StateCmdData(
                state = LoginState.Progress(
                    email = state.email,
                    password = state.password
                ),
                effect = ValidationEffect.Validate(
                    fields = listOf(
                        Field(
                            id = FIELD_ID_EMAIL,
                            type = FIELD_TYPE_EMAIL,
                            value = state.email.value
                        ),
                        Field(
                            id = FIELD_ID_PASSWORD,
                            type = FIELD_TYPE_PASSWORD,
                            value = state.password.fieldState.value
                        )
                    )
                )
            )
        }
        is LoginMessage.Success -> {
            StateCmdData(
                state = state,
                effect = NavigationEffect.NavigateToHome
            )
        }
        is LoginMessage.Error -> {
            StateCmdData(
                state = LoginState.Edit(
                    email = state.email,
                    password = state.password
                ),
                effect = GeneralEffect.ShowUserMessage(message.e.message.orEmpty())
            )
        }
        is ValidationMessage.Error -> {
            state as LoginState.Progress
            var tmp: LoginState.Progress = state
            message.wrongFields.forEach {
                when (it.id) {
                    FIELD_ID_EMAIL -> tmp = tmp.copy(email = processError(state.email, "email error"))
                    FIELD_ID_PASSWORD -> tmp = tmp.copy(
                        password = state.password.copy(
                            fieldState = processError(state.password.fieldState, "password error")
                        )
                    )
                }
            }
            state.updateWithoutCmd {
                LoginState.Edit(
                    email = tmp.email,
                    password = tmp.password
                )
            }
        }
        is ValidationMessage.Success -> {
            StateCmdData(
                state = state,
                effect = LoginEffect.Login(
                    LoginDTO(
                        email = state.email.value,
                        password = state.password.fieldState.value
                    )
                )
            )
        }
        else -> {
            state.updateWithoutCmd()
        }
    }
}

private fun processPasswordVisibleClick(state: LoginState): StateCmdData<LoginState> {
    return state.updateWithoutCmd {
        when (it) {
            is LoginState.Edit -> {
                it.copy(
                    password = state.password.copy(
                        visible = state.password.visible.not()
                    )
                )
            }
            else -> {
                throw createException()
            }
        }
    }
}

private fun processPasswordChanged(
    state: LoginState,
    message: LoginScreenMessage.PasswordChanged
): StateCmdData<LoginState> {
    return state.updateWithoutCmd {
        when (it) {
            is LoginState.Edit -> {
                it.copy(
                    password = it.password.copy(
                        fieldState = processTextFieldValueChanged(message.newValue)
                    )
                )
            }
            else -> {
                throw createException()
            }
        }
    }
}

private fun processEmailChanged(
    state: LoginState,
    message: LoginScreenMessage.EmailChanged
): StateCmdData<LoginState> {
    return state.updateWithoutCmd {
        when (it) {
            is LoginState.Edit -> {
                it.copy(
                    email = processTextFieldValueChanged(message.newValue)
                )
            }
            else -> {
                throw createException()
            }
        }
    }
}

fun createException() = IllegalStateException("message not supported on state not LoginState.Edit")