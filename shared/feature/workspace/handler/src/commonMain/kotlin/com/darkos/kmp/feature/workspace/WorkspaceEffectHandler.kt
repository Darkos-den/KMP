package com.darkos.kmp.feature.workspace

import com.darkos.kmp.feature.workspace.api.IWorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.api.IWorkspaceNavigation
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message

class WorkspaceEffectHandler(
    private val navigation: IWorkspaceNavigation
) : IWorkspaceEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}