package com.darkos.kts.feature.splash

import com.darkos.kts.feature.splash.model.mvu.SplashEffect
import com.darkos.kts.feature.splash.model.mvu.SplashMessage
import com.darkos.kts.feature.splash.model.mvu.SplashState
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

class SplashReducer : ISplashReducer {

    override fun update(state: SplashState, message: Message): StateCmdData<SplashState> {
        return when (message) {
            is ComponentInitialized -> {
                StateCmdData(
                    state = state,
                    effect = SplashEffect.CheckActiveUser
                )
            }
            is SplashMessage.UserFound -> {
                StateCmdData(
                    state = state,
                    effect = SplashEffect.Navigation.NavigateToMain
                )
            }
            is SplashMessage.UserNotFound -> {
                StateCmdData(
                    state = state,
                    effect = SplashEffect.Navigation.NavigateToLogin
                )
            }
            else -> StateCmdData(
                state = state,
                effect = None
            )
        }
    }
}