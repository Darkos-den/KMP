package com.darkos.kmp.feature.timer.api.model

import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.ScopedEffect
import com.darkos.mvu.model.flow.FlowEffect

sealed class TimerEffect: Effect() {

    object ShowSuccessMessage: TimerEffect()

    sealed class Trigger: TimerEffect(), ScopedEffect{
        override val scope = "timer_trigger"

        class Start(val value: Int): Trigger(), FlowEffect
        object Stop: Trigger()
    }
}