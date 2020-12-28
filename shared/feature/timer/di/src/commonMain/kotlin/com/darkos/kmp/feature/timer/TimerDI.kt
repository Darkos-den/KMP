package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.ITimerEffectHandler
import com.darkos.kmp.feature.timer.api.ITimerReducer
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.*

class TimerDI {

    @OptIn(InternalCoroutinesApi::class)
    val module = DI.Module(TAG){
        bind<ITimerReducer>() with provider { TimerReducer() }
        bind<ITimerEffectHandler>() with provider {
            TimerEffectHandler(
                alertProcessor = instance(),
                navigator = instance()
            )
        }
        bind<ITimerComponent>() with provider {
            TimerComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object{
        private const val TAG = "Timer"
    }
}