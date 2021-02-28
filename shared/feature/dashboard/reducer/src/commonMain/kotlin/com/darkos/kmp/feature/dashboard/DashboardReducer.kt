package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class DashboardReducer : IDashboardReducer {

    override fun update(state: DashboardState, message: Message): StateCmdData<DashboardState> {
        return when (message) {
            is ComponentInitialized -> {
                state.none()
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}