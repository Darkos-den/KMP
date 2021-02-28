package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.feature.signin.model.SignInState
import com.darkos.mvu.component.ProgramComponent

interface ISignInComponent : ProgramComponent<SignInState> {
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