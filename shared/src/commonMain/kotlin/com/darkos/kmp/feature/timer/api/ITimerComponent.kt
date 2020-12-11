package com.darkos.kmp.feature.timer.api

import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.flow.StateFlow

interface ITimerComponent {
    fun onTimerValueChanged(value: Int)
    fun onStartClick()
    fun onStopClick()
    fun onTextChanged(txt: String)

    fun createInitialState(): TimerState

    fun applyStateListener(block: (TimerState)->Unit)

    fun start()
}