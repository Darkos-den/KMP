package com.darkos.kmp

import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.TimerDiFacade
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        bind() from singleton {
            AlertProcessor()
        }
        bind<com.darkos.kmp.feature.timer.api.AlertProcessor>() with provider {
            instance<AlertProcessor>()
        }
        import(createAppModule())
    }

    fun timerDiFacade() = TimerDiFacade(diContainer.direct)

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()

    fun createAppModule() = DI.Module("app") {
        importAll(
            TimerDI().module
        )
    }
}