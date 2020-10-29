package com.darkos.kts.feature.auth

import com.darkos.core.model.navigation.Navigator
import com.darkos.core.mvu.andThenIdle
import com.darkos.kts.feature.auth.model.AuthNavigation
import com.darkos.kts.feature.auth.model.mvu.AuthEffect
import com.darkos.kts.feature.signin.LoginByEmailEffectHandler
import com.darkos.kts.feature.signin.LoginByEmailRemote
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
import com.darkos.mvu.validation.IValidationHandler

class AuthEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler,
    private val navigator: Navigator
) : LoginByEmailEffectHandler(remote, validation), IAuthEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is AuthEffect.Navigation.NavigateToSignIn -> {
                navigator.navigate(AuthNavigation.NavigateToSignIn)
                    .andThenIdle()
            }
            else -> {
                super.call(effect)
            }
        }
    }

    override fun processError(error: Exception): Message {
        TODO("Not yet implemented")
    }

    override fun processSuccess(): Message {
        TODO("Not yet implemented")
    }

    override suspend fun processLoginResult(result: TokenDTO) {
        TODO("Not yet implemented")
    }
}