package com.darkos.kmp.feature.workspace.model

import com.darkos.mvu.model.Effect

sealed class WorkspaceEffect : Effect() {
    object AddItem : WorkspaceEffect()
    object OpenSettings: WorkspaceEffect()
}