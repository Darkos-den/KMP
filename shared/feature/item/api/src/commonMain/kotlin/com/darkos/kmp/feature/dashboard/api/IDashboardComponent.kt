package com.darkos.kmp.feature.dashboard.api

import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.kmp.feature.drawer.api.IDrawerComponentDelegate
import com.darkos.mvu.component.ProgramComponent

interface IDashboardComponent : ProgramComponent<DashboardState>, IDrawerComponentDelegate {
    fun onAddClick()
    fun onSettingsClick()
}