package com.darkos.kmp.feature.drawer.di

import com.darkos.kmp.feature.drawer.DrawerEffectHandler
import com.darkos.kmp.feature.drawer.DrawerReducer
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class DrawerDI {

    val module = DI.Module(TAG) {
        bind<IDrawerReducer>() with provider { DrawerReducer() }
        bind<IDrawerEffectHandler>() with provider {
            DrawerEffectHandler(
                authManager = instance(),
                navigation = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Drawer"
    }
}