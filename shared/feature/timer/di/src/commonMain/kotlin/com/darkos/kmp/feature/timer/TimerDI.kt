package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.ITimerEffectHandler
import com.darkos.kmp.feature.timer.api.ITimerReducer
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class TimerDI {

    @InternalCoroutinesApi
    fun getModule() = DI.Module(TAG){
        bind<ITimerReducer>() with provider { TimerReducer() }
        bind<ITimerEffectHandler>() with provider { TimerEffectHandler() }
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