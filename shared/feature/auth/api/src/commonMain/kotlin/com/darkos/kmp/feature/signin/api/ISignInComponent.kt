package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.common.mvu.BaseComponent
import com.darkos.kmp.feature.signin.model.SignInState

interface ISignInComponent : BaseComponent<SignInState> {
    fun onSubmitClick()
    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
}