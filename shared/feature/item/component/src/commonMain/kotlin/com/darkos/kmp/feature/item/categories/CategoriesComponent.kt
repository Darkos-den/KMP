package com.darkos.kmp.feature.item.categories

import com.darkos.kmp.feature.item.categories.api.ICategoriesComponent
import com.darkos.kmp.feature.item.categories.api.ICategoriesEffectHandler
import com.darkos.kmp.feature.item.categories.api.ICategoriesReducer
import com.darkos.kmp.feature.item.categories.model.CategoriesMessage
import com.darkos.kmp.feature.item.categories.model.CategoriesState
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.mvu.component.MVUComponent

class CategoriesComponent(
    reducer: ICategoriesReducer,
    effectHandler: ICategoriesEffectHandler
) : MVUComponent<CategoriesState>(
    reducer = reducer,
    effectHandler = effectHandler
), ICategoriesComponent {

    override fun createInitialState(): CategoriesState {
        return CategoriesState()
    }

    override fun onLogoutClick() {
        accept(DrawerMessage.LogoutClicked)
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