package com.darkos.kts.feature.signin

import com.darkos.kts.feature.signin.model.Constants
import com.darkos.kts.feature.signin.model.dto.LoginDTO
import com.darkos.kts.feature.signin.model.mvu.LoginByEmailState
import com.darkos.kts.feature.signin.model.mvu.LoginByEmailMessage
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
                    valueChangedMessage = LoginByEmailMessage.PasswordChanged::class,
                    mapTo = { state, field ->
                        state.copy(password = field.value)
                    }
                )
            }
            if (!validateEmail) {
                registerField(
                    valueChangedMessage = LoginByEmailMessage.EmailChanged::class,
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
                    messageClass = LoginByEmailMessage.EmailChanged::class,
                    map = {
                        it.email.trim()
                    }
                )
            }
            if (validatePassword) {
                registerField(
                    fieldId = FIELD_ID_PASSWORD,
                    fieldType = LoginReducer.FieldType.Custom(Constants.FIELD_TYPE_PASSWORD),
                    messageClass = LoginByEmailMessage.PasswordChanged::class,
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