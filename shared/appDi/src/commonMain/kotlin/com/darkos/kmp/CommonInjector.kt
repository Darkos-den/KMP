package com.darkos.kmp

import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.TimerDiFacade
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        bind() from singleton {
            AlertProcessor()
        }
        bind<com.darkos.kmp.feature.timer.api.alertProcessor.AlertProcessor>() with provider {
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