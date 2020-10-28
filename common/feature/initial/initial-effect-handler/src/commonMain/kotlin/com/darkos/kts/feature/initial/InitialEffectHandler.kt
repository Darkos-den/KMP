package com.darkos.kts.feature.initial

import com.darkos.core.model.navigation.Navigator
import com.darkos.kts.feature.initial.IInitialEffectHandler
import com.darkos.kts.feature.initial.model.mvu.InitialEffect
import com.darkos.kts.feature.initial.model.mvu.InitialNavigation
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Idle
import com.darkos.mvu.models.Message

class InitialEffectHandler(
    private val navigator: Navigator
) : IInitialEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is InitialEffect.Navigation.NavigateToSplash -> {
                navigator.navigate(InitialNavigation.NavigateToSplash)
                Idle()
            }
            else -> Idle()
        }
    }
}