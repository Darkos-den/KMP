package com.darkos.kts.android.di

import com.darkos.kts.feature.auth.AuthRouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object NavigationModule {

    fun get() = Kodein.Module("App.Navigation") {
        bind(tag = AuthRouter.TAG) from provider {
            AuthRouter(
                appNavigator = instance()
            )
        }
    }
}