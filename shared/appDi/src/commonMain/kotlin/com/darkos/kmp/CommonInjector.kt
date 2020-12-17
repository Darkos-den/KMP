package com.darkos.kmp

import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.TimerDiFacade
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

class CommonInjector {

    private val diContainer = DI.lazy {
        import(createAppModule())
        bind() from singleton {
            AlertProcessor()
        }
    }

    fun timerDiFacade() = TimerDiFacade(diContainer.direct)

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()

    fun createAppModule() = DI.Module("app") {
        importAll(
            TimerDI().module
        )
    }
}