package com.darkos.kmp.feature.dashboard.di

import com.darkos.kmp.feature.dashboard.api.IDashboardComponent
import org.kodein.di.DirectDI
import org.kodein.di.instance

class DashboardDiFacade(private val directDI: DirectDI) {
    fun getComponent(): IDashboardComponent = directDI.instance()
}