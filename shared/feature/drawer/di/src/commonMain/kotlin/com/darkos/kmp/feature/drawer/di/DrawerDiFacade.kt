package com.darkos.kmp.feature.drawer.di

import com.darkos.kmp.feature.drawer.api.IDrawerComponent
import org.kodein.di.DirectDI
import org.kodein.di.instance

class DrawerDiFacade(private val directDI: DirectDI) {
    fun getComponent(): IDrawerComponent = directDI.instance()
}