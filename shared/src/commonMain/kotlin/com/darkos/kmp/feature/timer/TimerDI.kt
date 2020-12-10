package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent

class TimerDI {

    fun getComponent(): ITimerComponent {
        return TimerComponent(
            effectHandler = TimerEffectHandler(),
            reducer = TimerReducer()
        )
    }
}