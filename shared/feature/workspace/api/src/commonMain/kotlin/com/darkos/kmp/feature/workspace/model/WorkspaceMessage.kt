package com.darkos.kmp.feature.workspace.model

import com.darkos.mvu.model.Message

sealed class WorkspaceMessage : Message() {
    object AddClick : WorkspaceMessage()
    object SettingsClick: WorkspaceMessage()
}