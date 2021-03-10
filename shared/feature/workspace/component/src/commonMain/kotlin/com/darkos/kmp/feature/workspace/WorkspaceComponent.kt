package com.darkos.kmp.feature.workspace

import com.darkos.kmp.feature.workspace.api.IWorkspaceComponent
import com.darkos.kmp.feature.workspace.api.IWorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.api.IWorkspaceReducer
import com.darkos.kmp.feature.workspace.model.WorkspaceState
import com.darkos.mvu.component.MVUComponent

class WorkspaceComponent(
    reducer: IWorkspaceReducer,
    effectHandler: IWorkspaceEffectHandler
) : MVUComponent<WorkspaceState>(
    reducer = reducer,
    effectHandler = effectHandler
), IWorkspaceComponent {

    override fun createInitialState(): WorkspaceState {
        return WorkspaceState()
    }
}