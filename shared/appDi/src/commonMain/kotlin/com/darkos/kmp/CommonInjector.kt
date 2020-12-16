package com.darkos.kmp

import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.TimerDiFacade
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

class CommonInjector {

    private val diContainer = DI.lazy {
        import(createAppModule())
    }

    fun timerDiFacade() = TimerDiFacade(diContainer.direct)

    fun createAppModule() = DI.Module("app") {
        importAll(
            TimerDI().module
        )
    }
}