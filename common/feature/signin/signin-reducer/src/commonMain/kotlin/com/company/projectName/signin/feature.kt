package com.company.projectName.signin

import com.company.projectName.login.LoginFeature
import com.company.projectName.login.LoginReducer
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.mvu.SignInMessage
import com.company.projectName.signin.model.mvu.SignInScreenState

private const val FIELD_ID_EMAIL: Long = 1
private const val FIELD_ID_PASSWORD: Long = 2

val signInReducer = LoginFeature<SignInScreenState, LoginDTO> {
    MapStateToLoginRequest {
        LoginDTO(
            email = it.email,
            password = it.password
        )
    }
    ProcessProgress { state, value ->
        state.copy(progress = value)
    }
    WithoutValidation {
        registerField(
            valueChangedMessage = SignInMessage.PasswordChanged::class,
            mapTo = { state, field ->
                state.copy(password = field.value)
            }
        )
    }
    WithValidation {
        registerField(
            fieldId = FIELD_ID_EMAIL,
            fieldType = LoginReducer.FieldType.Email,
            messageClass = SignInMessage.EmailChanged::class,
            map = {
                it.email.trim()
            }
        )

        RegisterValidationMapper { state, fields ->
            state.copy(
                emailError = fields.firstOrNull {
                    it.id == FIELD_ID_EMAIL
                }?.takeIf { it.valid.not() }?.let {
                    "Email error"
                } ?: ""
            )
        }
    }
}
