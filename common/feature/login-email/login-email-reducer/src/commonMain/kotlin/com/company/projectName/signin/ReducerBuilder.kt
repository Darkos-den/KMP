package com.company.projectName.signin

import com.company.projectName.signin.model.Constants
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.mvu.LoginByEmailState
import com.company.projectName.signin.model.mvu.SignInMessage
import com.darkos.mvu.login.LoginFeature
import com.darkos.mvu.login.LoginReducer

class ReducerBuilder {
    private var validateEmail = false
    private var validatePassword = false

    fun enableEmailValidation() {
        validateEmail = true
    }

    fun enablePasswordValidation() {
        validatePassword = true
    }

    fun build() = LoginFeature<LoginByEmailState, LoginDTO> {
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
            if (!validatePassword) {
                registerField(
                    valueChangedMessage = SignInMessage.PasswordChanged::class,
                    mapTo = { state, field ->
                        state.copy(password = field.value)
                    }
                )
            }
            if (!validateEmail) {
                registerField(
                    valueChangedMessage = SignInMessage.EmailChanged::class,
                    mapTo = { state, field ->
                        state.copy(email = field.value)
                    }
                )
            }
        }
        WithValidation {
            if (validateEmail) {
                registerField(
                    fieldId = FIELD_ID_EMAIL,
                    fieldType = LoginReducer.FieldType.Email,
                    messageClass = SignInMessage.EmailChanged::class,
                    map = {
                        it.email.trim()
                    }
                )
            }
            if (validatePassword) {
                registerField(
                    fieldId = FIELD_ID_PASSWORD,
                    fieldType = LoginReducer.FieldType.Custom(Constants.FIELD_TYPE_PASSWORD),
                    messageClass = SignInMessage.PasswordChanged::class,
                    map = {
                        it.password.trim()
                    }
                )
            }

            RegisterValidationMapper { state, fields ->
                state.copy(
                    emailError = fields.firstOrNull {
                        it.id == FIELD_ID_EMAIL
                    }?.takeIf { it.valid.not() }?.let {
                        "Wrong email"
                    } ?: "",
                    passwordError = fields.firstOrNull {
                        it.id == FIELD_ID_PASSWORD
                    }?.takeIf { it.valid.not() }?.let {
                        "Wrong password"
                    } ?: ""
                )
            }
        }
    }

    companion object {
        private const val FIELD_ID_EMAIL: Long = 1
        private const val FIELD_ID_PASSWORD: Long = 2
    }
}