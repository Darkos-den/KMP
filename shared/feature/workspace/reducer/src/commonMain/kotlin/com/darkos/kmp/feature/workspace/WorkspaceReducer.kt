package com.darkos.kmp.feature.workspace

import com.darkos.kmp.feature.workspace.api.IWorkspaceReducer
import com.darkos.kmp.feature.workspace.model.WorkspaceState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class WorkspaceReducer : IWorkspaceReducer {

    override fun update(state: WorkspaceState, message: Message): StateCmdData<WorkspaceState> {
        return when (message) {
            is ComponentInitialized -> state.none()
            is Idle -> state.none()
            is RestoreState<*> -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}