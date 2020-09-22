package com.company.projectName.domain.feature.login

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.feature.textField.processError
import com.company.projectName.domain.feature.textField.processTextFieldValueChanged
import com.company.projectName.domain.model.dto.LoginDTO
import com.company.projectName.domain.model.mvu.auth.AuthEffect
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.domain.model.mvu.auth.AuthMessage
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val loginReducer = reducer<LoginState> { state, message ->
    when (message) {
        is LoginMessage.EmailChanged -> {
            processEmailChanged(state, message)
        }
        is LoginMessage.PasswordChanged -> {
            processPasswordChanged(state, message)
        }
        is LoginMessage.PasswordVisibleClick -> {
            processPasswordVisibleClick(state)
        }
        is LoginMessage.SubmitClick -> {
            StateCmdData(
                state = LoginState.Progress(
                    email = state.email,
                    password = state.password
                ),
                effect = AuthEffect.ValidateLoginData(
                    dto = LoginDTO(
                        email = state.email.value,
                        password = state.password.fieldState.value
                    )
                )
            )
        }
        is AuthMessage.LoginValidation.Success -> {
            StateCmdData(
                state = state,
                effect = AuthEffect.Login(
                    dto = LoginDTO(
                        email = state.email.value,
                        password = state.password.fieldState.value
                    )
                )
            )
        }
        is AuthMessage.LoginValidation.Error -> {
            processLoginValidationError(state, message)
        }
        is AuthMessage.Login.Success -> {
            StateCmdData(
                state = state,
                effect = NavigationEffect.NavigateToHome
            )
        }
        is AuthMessage.Login.Error -> {
            StateCmdData(
                state = LoginState.Edit(
                    email = state.email,
                    password = state.password
                ),
                effect = GeneralEffect.ShowUserMessage(message.message)
            )
        }
        else -> {
            state.updateWithoutCmd()
        }
    }
}

private fun processLoginValidationError(
    state: LoginState,
    message: AuthMessage.LoginValidation.Error
): StateCmdData<LoginState> {
    return state.updateWithoutCmd {
        LoginState.Edit(
            email = processError(state.email, message.emailError),
            password = state.password.copy(
                fieldState = processError(state.password.fieldState, message.passwordError)
            )
        )
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
    message: LoginMessage.PasswordChanged
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
    message: LoginMessage.EmailChanged
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