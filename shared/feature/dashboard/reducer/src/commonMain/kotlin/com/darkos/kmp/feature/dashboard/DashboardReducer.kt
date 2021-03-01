package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardEffect
import com.darkos.kmp.feature.dashboard.model.DashboardMessage
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class DashboardReducer : IDashboardReducer {

    override fun update(state: DashboardState, message: Message): StateCmdData<DashboardState> {
        return when (message) {
            is ComponentInitialized -> {
                state.none()
            }
            is Idle -> state.none()
//            is DashboardMessage.LogoutClicked -> {
//                state andEffect DashboardEffect.Logout
//            }
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}