package com.darkos.kmp.feature.drawer.api

import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.component.ProgramComponent

interface IDrawerComponentDelegate {
    fun onLogoutClick()
    fun onDashboardClick()
    fun onCategoriesClick()
    fun onSearchClick()
    fun onProfileClick()
    fun onContactClick()
}