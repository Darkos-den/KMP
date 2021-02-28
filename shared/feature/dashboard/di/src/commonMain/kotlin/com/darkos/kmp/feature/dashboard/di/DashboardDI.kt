package com.darkos.kmp.feature.dashboard.di

import com.darkos.kmp.feature.dashboard.DashboardComponent
import com.darkos.kmp.feature.dashboard.DashboardEffectHandler
import com.darkos.kmp.feature.dashboard.DashboardReducer
import com.darkos.kmp.feature.dashboard.api.IDashboardComponent
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.kmp.feature.dashboard.api.IDashboardReducer
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class DashboardDI {

    @OptIn(InternalCoroutinesApi::class)
    val module = DI.Module(TAG) {
        bind<IDashboardReducer>() with provider { DashboardReducer() }
        bind<IDashboardEffectHandler>() with provider {
            DashboardEffectHandler()
        }
        bind<IDashboardComponent>() with provider {
            DashboardComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Dashboard"
    }
}