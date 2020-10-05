package com.company.projectName.domain.feature.splash

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.core.model.navigation.NavigationEffect
import com.company.projectName.domain.model.mvu.auth.AuthMessage
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val splashReducer = reducer<SplashState> { state, message ->
    when (message) {
        is ComponentInitialized -> {
            StateCmdData(
                state = state,
                effect = /*AuthEffect.CheckUser*/None()//todo
            )
        }
        is AuthMessage.CheckUser.Found -> {
            StateCmdData(
                state = state,
                effect = NavigationEffect.NavigateToHome
            )
        }
        is AuthMessage.CheckUser.NotFound -> {
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