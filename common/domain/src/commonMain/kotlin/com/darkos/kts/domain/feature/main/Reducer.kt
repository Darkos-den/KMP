package com.darkos.kts.domain.feature.main

import com.darkos.kts.domain.common.updateWithoutCmd
import com.darkos.core.model.navigation.NavigationEffect
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