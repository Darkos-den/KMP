package com.darkos.kts.domain.effectHandler

import com.darkos.core.model.navigation.Navigator
import com.darkos.core.model.navigation.NavigationEffect
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Idle
import com.darkos.mvu.models.Message

internal class NavigationEffectHandler(
    private val navigator: Navigator
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        check(effect is NavigationEffect) {
            "this effect handler allow only NavigationEffect"
        }

//        when (effect) {
//            is NavigationEffect.NavigateToSplash -> Navigation.Main.Splash
//            is NavigationEffect.NavigateToLogin -> Navigation.Main.Login
//            is NavigationEffect.NavigateToHome -> Navigation.Main.Home
//        }.let {
//            withContext(Dispatchers.Main) {
//                navigator.navigate(it)
//            }
//        }

        return Idle()
    }
}