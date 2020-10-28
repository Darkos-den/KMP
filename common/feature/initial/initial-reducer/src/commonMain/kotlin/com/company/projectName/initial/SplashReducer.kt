package com.company.projectName.initial

import com.company.projectName.initial.model.mvu.splash.SplashEffect
import com.company.projectName.initial.model.mvu.splash.SplashMessage
import com.company.projectName.initial.model.mvu.splash.SplashState
import com.company.projectName.initial.splash.ISplashReducer
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