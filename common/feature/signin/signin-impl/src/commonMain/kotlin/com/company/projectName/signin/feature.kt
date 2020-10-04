package com.company.projectName.signin

import com.company.core.model.general.GeneralEffect
import com.company.core.model.navigation.NavigationEffect
import com.company.projectName.login.LoginFeature
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.mvu.LoginScreenMessage
import com.company.projectName.signin.model.mvu.LoginScreenState
import com.darkos.mvu.validation.model.Field
import com.darkos.mvu.validation.model.ValidationFieldType

private const val FIELD_ID_EMAIL: Long = 1
private const val FIELD_ID_PASSWORD: Long = 2

val feature = LoginFeature<LoginScreenState, LoginDTO> {
    MapStateToLoginRequest {
        LoginDTO(
            email = it.email,
            password = it.password
        )
    }
    ProcessStatus {
        OnSuccess {
            NavigationEffect.NavigateToHome
        }
        OnFailed {
            GeneralEffect.ShowUserMessage(it.message ?: "Something went wrong")
        }
        OnStateChanged { state, value ->
            state.copy(progress = value)
        }
    }
    WithoutValidation {
        registerField(
            valueChangedMessage = LoginScreenMessage.PasswordChanged::class,
            mapTo = { state, field ->
                state.copy(password = field.value)
            }
        )
    }
    WithValidation {
        registerField(
            fieldId = FIELD_ID_EMAIL,
            valueChangedMessage = LoginScreenMessage.EmailChanged::class,
            map = {
                Field(
                    id = FIELD_ID_EMAIL,
                    type = ValidationFieldType.Email,
                    value = it.email.trim()
                )
            }
        )

        RegisterValidationMapper { state, fields ->
            state.copy(
                email = fields.fields.firstOrNull {
                    it.id == FIELD_ID_EMAIL
                }?.value ?: state.email,
                emailError = fields.fields.firstOrNull {
                    it.id == FIELD_ID_EMAIL
                }?.takeIf { it.status == FieldValidationStatus.INVALID }?.let {
                    "Email error"
                } ?: ""
            )
        }
    }
}
