package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.common.mvu.BaseComponent
import com.darkos.kmp.feature.signin.model.SignInState

interface ISignInComponent : BaseComponent<SignInState> {
    fun onSubmitClick()

    /**
     * @param value new email value
     */
    fun onEmailChanged(value: String)

    /**
     * @param value new password value
     */
    fun onPasswordChanged(value: String)
}