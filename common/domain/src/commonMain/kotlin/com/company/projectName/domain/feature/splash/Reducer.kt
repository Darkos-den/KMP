package com.company.projectName.domain.feature.splash

import com.company.projectName.domain.common.updateStateWithoutCmd
import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.model.effect.GeneralEffect
import com.company.projectName.domain.model.effect.NavigationEffect
import com.company.projectName.domain.model.message.GeneralMessage
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val splashReducer = reducer<SplashState> { state, message ->
    when (message) {
        is ComponentInitialized -> {
            //todo: check is user authorized
            StateCmdData(
                state = state,
                effect = GeneralEffect.Timer(SPLASH_DELAY)
            )
        }
        is GeneralMessage.TimerFinished -> {
            StateCmdData(
                state = state,
                effect = NavigationEffect.NavigateToLogin
            )
        }
        else -> {
            state.updateWithoutCmd()
        }
    }
}

private const val SPLASH_DELAY: Long = 2000