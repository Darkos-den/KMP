package com.darkos.kmp

import com.darkos.kmp.alertProcessor.AlertProcessor
import com.darkos.kmp.alertProcessor.IAlertProcessor
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        import(createAppModule())
    }

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()

    fun createAppModule() = DI.Module("app") {
        importAll(
            alertProcessorModule()
        )
    }

    private fun alertProcessorModule() = DI.Module("AlertProcessor") {
        bind() from singleton {
            AlertProcessor()
        }
        bind<IAlertProcessor>() with singleton {
            instance<AlertProcessor>()
        }
    }
}