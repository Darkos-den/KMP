package com.darkos.kmp.feature.workspace

import com.darkos.kmp.feature.workspace.api.IWorkspaceComponent
import com.darkos.kmp.feature.workspace.api.IWorkspaceEffectHandler
import com.darkos.kmp.feature.workspace.api.IWorkspaceReducer
import com.darkos.kmp.feature.workspace.model.WorkspaceMessage
import com.darkos.kmp.feature.workspace.model.WorkspaceState
import com.darkos.kmp.feature.drawer.model.DrawerMessage
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

    override fun onLogoutClick() {
        accept(DrawerMessage.LogoutClicked)
    }

    override fun onAddClick() {
        accept(WorkspaceMessage.AddClick)
    }

    override fun onSettingsClick() {
        accept(WorkspaceMessage.SettingsClick)
    }

    override fun onDashboardClick() {
        accept(DrawerMessage.DashboardClicked)
    }

    override fun onCategoriesClick() {
        accept(DrawerMessage.CategoriesClicked)
    }

    override fun onSearchClick() {
        accept(DrawerMessage.SearchClicked)
    }

    override fun onProfileClick() {
        accept(DrawerMessage.ProfileClicked)
    }

    override fun onContactClick() {
        accept(DrawerMessage.ContactClicked)
    }
}