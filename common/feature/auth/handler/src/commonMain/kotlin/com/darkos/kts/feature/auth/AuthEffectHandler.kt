package com.darkos.kts.feature.auth

import com.darkos.core.mvu.andThenIdle
import com.darkos.kts.feature.auth.model.AuthNavigation
import com.darkos.kts.feature.auth.model.mvu.AuthEffect
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
import com.darkos.mvu.navigator.Navigator

class AuthEffectHandler(
    private val navigator: Navigator
) : IAuthEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is AuthEffect.Navigation.NavigateToSignIn -> {
                navigator.navigate(AuthNavigation.NavigateToSignIn)
                    .andThenIdle()
            }
            else -> {
                throw IllegalArgumentException("effect not supported")
            }
        }
    }
}