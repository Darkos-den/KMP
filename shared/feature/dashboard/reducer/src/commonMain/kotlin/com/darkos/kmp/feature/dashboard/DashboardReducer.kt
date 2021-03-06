package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardEffect
import com.darkos.kmp.feature.dashboard.model.DashboardMessage
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class DashboardReducer(
    private val drawerReducer: IDrawerReducer
) : IDashboardReducer {

    override fun update(state: DashboardState, message: Message): StateCmdData<DashboardState> {
        return when (message) {
            is DrawerMessage -> {
                drawerReducer.update(state = DrawerState, message = message).let {
                    state andEffect it.effect
                }
            }
            is ComponentInitialized -> {
                state.none()
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}