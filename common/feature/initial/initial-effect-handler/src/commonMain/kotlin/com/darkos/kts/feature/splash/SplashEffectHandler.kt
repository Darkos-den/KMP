package com.darkos.kts.feature.splash

import com.darkos.core.mvu.andThenIdle
import com.darkos.core.navigation.Navigator
import com.darkos.kts.feature.splash.model.SplashNavigation
import com.darkos.kts.feature.splash.model.mvu.SplashEffect
import com.darkos.kts.feature.splash.model.mvu.SplashMessage
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Idle
import com.darkos.mvu.models.Message

class SplashEffectHandler(
    private val secure: ISplashSecure,
    private val navigator: Navigator
) : ISplashEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SplashEffect.CheckActiveUser -> {
                if (secure.isActiveUserFound()) {
                    SplashMessage.UserFound
                } else {
                    SplashMessage.UserNotFound
                }
            }
            is SplashEffect.Navigation.NavigateToLogin -> {
                navigator.navigate(SplashNavigation.NavigateToLogin)
                    .andThenIdle()
            }
            is SplashEffect.Navigation.NavigateToMain -> {
                navigator.navigate(SplashNavigation.NavigateToMain)
                    .andThenIdle()
            }
            else -> Idle()
        }
    }
}