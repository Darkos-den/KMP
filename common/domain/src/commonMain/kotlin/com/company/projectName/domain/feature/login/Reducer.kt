package com.company.projectName.domain.feature.login

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.effectHandler.ValidateEffectHandler.Companion.FIELD_TYPE_EMAIL
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.login.LoginFeature
import com.company.projectName.login.LoginReducer
import com.company.projectName.login.map
import com.company.projectName.login.model.mvu.LoginMessage
import com.company.projectName.validation.model.Field
import com.company.projectName.validation.model.FieldValidationStatus
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.reducer

private const val FIELD_ID_EMAIL: Long = 1
private const val FIELD_ID_PASSWORD: Long = 2

val feature = LoginFeature<LoginScreenState> {
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
                    type = FIELD_TYPE_EMAIL,
                    value = it.email
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

val loginReducer = reducer<LoginScreenState> { state, message ->
    if (message is ComponentInitialized) {
        state.updateWithoutCmd()
    } else {
        feature.update(state, message)
    }
}