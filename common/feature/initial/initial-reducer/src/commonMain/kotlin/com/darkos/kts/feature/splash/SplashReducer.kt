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
        //todo: implement check account logic
        return when (message) {
            is ComponentInitialized -> {
                StateCmdData(
                    state = state,
                    effect = SplashEffect.Delay(3000)
                )
            }
            is SplashMessage.DelayFinished -> {
                StateCmdData(
                    state = state,
                    effect = SplashEffect.Navigation.NavigateToLogin
                )
            }
            else -> StateCmdData(
                state = state,
                effect = None()
            )
        }
    }
}