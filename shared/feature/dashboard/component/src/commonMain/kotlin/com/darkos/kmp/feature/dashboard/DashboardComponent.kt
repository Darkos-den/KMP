package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardComponent
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState

class DashboardComponent(
    reducer: IDashboardReducer,
    effectHandler: IDashboardEffectHandler
) : MVUComponent<DashboardState>(
    reducer = reducer,
    effectHandler = effectHandler
), IDashboardComponent {

    override fun createInitialState(): DashboardState {
        return DashboardState()
    }

    override fun restore(state: DashboardState) {//todo: move to core
        accept(RestoreState(state))
    }
}