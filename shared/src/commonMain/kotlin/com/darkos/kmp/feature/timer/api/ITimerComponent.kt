package com.darkos.kmp.feature.timer.api

import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.flow.StateFlow

interface ITimerComponent {
    val state: StateFlow<TimerState>

    fun onTimerValueChanged(value: Int)
    fun onStartClick()
    fun onStopClick()
}