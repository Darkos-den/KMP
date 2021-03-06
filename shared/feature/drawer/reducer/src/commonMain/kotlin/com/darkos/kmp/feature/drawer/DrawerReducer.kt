package com.darkos.kmp.feature.drawer

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.kmp.common.errorHandler.processErrorMessage
import com.darkos.kmp.common.mvu.restoreStateAndEffect
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerEffect
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class DrawerReducer : IDrawerReducer {

    override fun update(state: DrawerState, message: Message): StateCmdData<DrawerState> {
        return when (message) {
            is ComponentInitialized -> {
                state.none()
            }
            is RestoreState<*> -> {
                state.none()
            }
            is ErrorMessage -> {
                processErrorMessage(message) {
                    state
                }
            }
            is DrawerMessage.LogoutClicked -> {
                state andEffect DrawerEffect.Logout
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}