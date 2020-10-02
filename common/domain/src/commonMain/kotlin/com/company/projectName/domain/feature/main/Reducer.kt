package com.company.projectName.domain.feature.main

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.core.model.navigation.NavigationEffect
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val mainReducer = reducer<MainState> { state, message ->
    when (message) {
        is ComponentInitialized -> {
            StateCmdData(
                state = state,
                effect = NavigationEffect.NavigateToSplash
            )
        }
        else -> {
            state.updateWithoutCmd()
        }
    }
}