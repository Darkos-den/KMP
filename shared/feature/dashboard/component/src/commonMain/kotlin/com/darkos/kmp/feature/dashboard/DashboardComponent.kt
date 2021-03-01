package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardComponent
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardMessage
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState
import com.darkos.kmp.feature.drawer.api

class DashboardComponent(
    reducer: IDashboardReducer,
    effectHandler: IDashboardEffectHandler
) : MVUComponent<DashboardState>(
    reducer = reducer,
    effectHandler = effectHandler
), IDashboardComponent, IDrawerComponent {

    override val x: com.darkos.kmp.feature.drawer.api.IDrawerComponent
        get() = TODO("Not yet implemented")

    override fun createInitialState(): DashboardState {
        return DashboardState()
    }

    override fun restore(state: DashboardState) {//todo: move to core
        accept(RestoreState(state))
    }


}