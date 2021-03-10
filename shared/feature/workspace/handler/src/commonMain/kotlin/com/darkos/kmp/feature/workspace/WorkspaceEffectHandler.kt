package com.darkos.kmp.feature.workspace

import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.workspace.api.IWorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.api.IWorkspaceNavigation
import com.darkos.kmp.feature.workspace.model.WorkspaceEffect
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.kmp.feature.drawer.model.DrawerEffect
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message

class WorkspaceEffectHandler(
    private val drawerEffectHandler: IDrawerEffectHandler,
    private val navigation: IWorkspaceNavigation
) : IWorkspaceEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is DrawerEffect -> drawerEffectHandler.call(effect)
            is WorkspaceEffect.AddItem -> navigate(navigation::fromDashboardToAddItem)
            is WorkspaceEffect.OpenSettings -> navigate(navigation::fromDashboardToWorkspace)
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}