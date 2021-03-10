package com.darkos.kmp.feature.workspace

import com.darkos.kmp.feature.workspace.api.IWorkspaceReducer
import com.darkos.kmp.feature.workspace.model.WorkspaceEffect
import com.darkos.kmp.feature.workspace.model.WorkspaceMessage
import com.darkos.kmp.feature.workspace.model.WorkspaceState
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerDestination
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class WorkspaceReducer(
    private val drawerReducer: IDrawerReducer
) : IWorkspaceReducer {

    override fun update(state: WorkspaceState, message: Message): StateCmdData<WorkspaceState> {
        return when (message) {
            is DrawerMessage -> drawerReducer.update(
                state = DrawerState(DrawerDestination.Dashboard),
                message = message
            ) replaceState state
            is ComponentInitialized -> state.none()
            is WorkspaceMessage.AddClick -> state andEffect WorkspaceEffect.AddItem
            is WorkspaceMessage.SettingsClick -> state andEffect WorkspaceEffect.OpenSettings
            is Idle -> state.none()
            is RestoreState<*> -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}