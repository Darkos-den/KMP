package com.darkos.kmp.feature.workspace.di

import com.darkos.kmp.feature.workspace.WorkspaceComponent
import com.darkos.kmp.feature.workspace.WorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.WorkspaceReducer
import com.darkos.kmp.feature.workspace.api.IWorkspaceComponent
import com.darkos.kmp.feature.workspace.api.IWorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.api.IWorkspaceReducer
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class WorkspaceDI {

    val module = DI.Module(TAG) {
        bind<IWorkspaceReducer>() with provider {
            WorkspaceReducer(
                drawerReducer = instance()
            )
        }
        bind<IWorkspaceEffectHandler>() with provider {
            WorkspaceEffectHandler(
                drawerEffectHandler = instance(),
                navigation = instance()
            )
        }
        bind<IWorkspaceComponent>() with provider {
            WorkspaceComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Dashboard"
    }
}