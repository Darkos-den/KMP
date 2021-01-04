package com.darkos.kmp

import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.TimerDiFacade
import com.darkos.kmp.alertProcessor.AlertProcessor
import com.darkos.kmp.alertProcessor.IAlertProcessor
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        import(createAppModule())
    }

    fun timerDiFacade() = TimerDiFacade(diContainer.direct)

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()

    fun createAppModule() = DI.Module("app") {
        importAll(
            alertProcessorModule(),
            TimerDI().module
        )
    }

    private fun alertProcessorModule() = DI.Module("AlertProcessor"){
        bind() from singleton {
            AlertProcessor()
        }
        bind<IAlertProcessor>() with singleton {
            instance<AlertProcessor>()
        }
    }
}