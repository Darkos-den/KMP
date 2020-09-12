package com.company.projectName.domain.feature.splash

import com.company.projectName.domain.common.updateStateWithoutCmd
import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.model.effect.AuthEffect
import com.company.projectName.domain.model.effect.GeneralEffect
import com.company.projectName.domain.model.effect.NavigationEffect
import com.company.projectName.domain.model.message.AuthMessage
import com.company.projectName.domain.model.message.GeneralMessage
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val splashReducer = reducer<SplashState> { state, message ->
    when (message) {
        is ComponentInitialized -> {
            StateCmdData(
                state = state,
                effect = AuthEffect.CheckUser
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