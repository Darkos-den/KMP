package com.darkos.kmp.feature.signin.model

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle

sealed class SignInEffect : Effect() {

    /**
     * Single effect, initiating the process of data validation
     * and user authorization using the transmitted data.
     *
     * Possible final messages:
     * @see SignInMessage.ValidationError in case there was a validation error
     * @see ErrorMessage.Network in case there is no Internet during authorization
     * @see ErrorMessage.App in case of an error while processing the authorization request
     * @see Idle if the script is processed without errors
     */
    class ProcessSignIn(
        val email: String,
        val password: String
    ) : SignInEffect()
}