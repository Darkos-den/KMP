package com.company.projectName.auth

import com.company.projectName.auth.model.mvu.signin.SignInScreenState
import com.company.projectName.signin.LoginByEmailReducer
import com.company.projectName.signin.model.mvu.LoginByEmailState
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

//class SignInReducer: Reducer<SignInScreenState> {
//
//    private val loginByEmailReducer = LoginByEmailReducer {
//        enableEmailValidation()
//    }
//
//    private fun mapToAuthState(state: LoginByEmailState): SignInScreenState {
//        return SignInScreenState(
//            email = state.email,
//            password = state.password,
//            emailError = state.emailError,
//            passwordError = state.passwordError,
//            progress = state.progress
//        )
//    }
//
//    private fun mapToSignInState(state: SignInScreenState): LoginByEmailState {
//        return LoginByEmailState(
//            email = state.email,
//            password = state.password,
//            emailError = state.emailError,
//            passwordError = state.passwordError,
//            progress = state.progress
//        )
//    }
//
//    override fun update(
//        state: SignInScreenState,
//        message: Message
//    ): StateCmdData<SignInScreenState> {
//        return when (val translated = translate(message)) {
//            is SignInMessage -> {
//                loginByEmailReducer.update(
//                    state = mapToSignInState(state),
//                    message = translated`
//                ).map(this::mapToAuthState)
//            }
//            is com.company.projectName.auth.model.mvu.signin.AuthMessage.SignInMessage.SignUpClick -> {
//                TODO("navigate to sign up")
//            }
//            else -> StateCmdData(
//                state = state,
//                effect = None()
//            )
//        }
//    }
//
//    private fun translate(message: Message): Message {
//        return when (message) {
//            is com.company.projectName.auth.model.mvu.signin.AuthMessage.SignInMessage.EmailChanged -> SignInMessage.EmailChanged(
//                message.value
//            )
//            is com.company.projectName.auth.model.mvu.signin.AuthMessage.SignInMessage.PasswordChanged -> SignInMessage.PasswordChanged(
//                message.value
//            )
//            else -> message
//        }
//    }
//}