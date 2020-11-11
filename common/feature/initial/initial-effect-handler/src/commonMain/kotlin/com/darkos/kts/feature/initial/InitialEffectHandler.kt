package com.darkos.kts.feature.initial

import com.darkos.core.mvu.andThenIdle
import com.darkos.kts.feature.initial.model.InitialNavigation
import com.darkos.kts.feature.initial.model.mvu.InitialEffect
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Idle
import com.darkos.mvu.models.Message
import com.darkos.mvu.navigator.Navigator

class InitialEffectHandler(
    private val navigator: Navigator
) : IInitialEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is InitialEffect.Navigation.NavigateToSplash -> {
                navigator.navigate(InitialNavigation.NavigateToSplash)
                    .andThenIdle()
            }
            else -> Idle
        }
    }
}