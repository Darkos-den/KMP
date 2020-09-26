package com.company.projectName.login

import com.company.projectName.login.model.EmailPassword
import com.company.projectName.login.model.LoginState
import com.company.projectName.validation.model.Field
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message

sealed class EmailPasswordMessage : Message() {
    class EmailChanged(val newValue: String) : EmailPasswordMessage()
    class PasswordChanged(val newValue: String) : EmailPasswordMessage()
}

data class FeatureDataMapper<T : Any>(
    val from: (T) -> EmailPassword,
    val to: (T, EmailPassword) -> T
)

//fun x() {
//    Login<State> {
//        Values {
//            registerField(
//                changeValueMessageClass = EmailPasswordMessage.EmailChanged::class,
//                onChange = {
//                    it.copy()
//                }
//            )
//        }
//        ValidateValues {
//            registerField(
//                changeValueMessageClass = EmailPasswordMessage.PasswordChanged::class,
//                onChange = {
//                    it.copy()
//                }
//            )
//
//            processError {
//                it.copy()
//            }
//        }
//        ProcessStatus(
//            successEffect = {
//                NavigationEffect.NavigateToHome
//            },
//            failedEffect = { _, loginFailed ->
//                GeneralEffect.ShowUserMessage(loginFailed.message.orEmpty())
//            },
//            onStateChanged = {
//                it.copy()
//            }
//        )
//    }
//}

class ValidationState(
    val value: String,
    val error: String
)

interface Validatable