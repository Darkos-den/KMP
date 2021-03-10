package com.darkos.kmp.feature.workspace.api

import com.darkos.kmp.feature.workspace.model.WorkspaceState
import com.darkos.kmp.feature.drawer.api.IDrawerComponentDelegate
import com.darkos.mvu.component.ProgramComponent

interface IWorkspaceComponent : ProgramComponent<WorkspaceState>, IDrawerComponentDelegate {
    fun onAddClick()
    fun onSettingsClick()
}