package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent
import kotlinx.coroutines.InternalCoroutinesApi

class TimerDI {

    @InternalCoroutinesApi
    fun getComponent(): ITimerComponent {
        return TimerComponent(
            effectHandler = TimerEffectHandler(),
            reducer = TimerReducer()
        )
    }
}