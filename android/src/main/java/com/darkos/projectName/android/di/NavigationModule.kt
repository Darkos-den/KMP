package com.darkos.projectName.android.di

import com.darkos.projectName.android.ui.main.MainRouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object NavigationModule {
    fun get() = Kodein.Module("Navigation"){
        bind() from singleton {
            MainRouter(
                appNavigator = instance()
            )
        }
    }
}