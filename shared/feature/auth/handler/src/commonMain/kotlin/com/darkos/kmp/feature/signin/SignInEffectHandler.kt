package com.darkos.kmp.feature.signin

import com.darkos.kmp.feature.signin.api.ISignInEffectHandler
import com.darkos.kmp.feature.signin.model.SignInEffect
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.None

class SignInEffectHandler : ISignInEffectHandler {

    class ValidationException : Exception()

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is None -> {
                Idle
            }
            is SignInEffect.ProcessSignIn -> {
                try {
                    validateSignInData(effect.email, effect.password)
                } catch (e: ValidationException) {
                    TODO()
                }
                TODO()
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    private fun validateSignInData(email: String, password: String) {
        TODO()
    }

    private fun validateEmail(email: String): Boolean {
        TODO()
    }
}