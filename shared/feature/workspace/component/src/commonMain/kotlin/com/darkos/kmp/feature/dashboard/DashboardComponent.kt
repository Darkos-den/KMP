package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.feature.dashboard.api.IDashboardComponent
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import com.darkos.kmp.feature.dashboard.model.DashboardMessage
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.kmp.feature.drawer.api.IDrawerComponentDelegate
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState

class DashboardComponent(
    reducer: IDashboardReducer,
    effectHandler: IDashboardEffectHandler
) : MVUComponent<DashboardState>(
    reducer = reducer,
    effectHandler = effectHandler
), IDashboardComponent {

    override fun createInitialState(): DashboardState {
        return DashboardState()
    }

    override fun onLogoutClick() {
        accept(DrawerMessage.LogoutClicked)
    }

    override fun onAddClick() {
        accept(DashboardMessage.AddClick)
    }

    override fun onSettingsClick() {
        accept(DashboardMessage.SettingsClick)
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