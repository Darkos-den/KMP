package com.darkos.kts.android.di

import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.kts.feature.auth.AuthRouter
import com.darkos.kts.feature.initial.InitialRouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object NavigationModule {

    fun get() = Kodein.Module("App.Navigation"){
        bind() from singleton {
            InitialRouter(
                appNavigator = instance()
            )
        }
        bind() from singleton {
            AuthRouter(
                appNavigator = instance()
            )
        }
    }
}