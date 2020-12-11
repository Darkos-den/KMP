package com.darkos.kmp.feature.timer.api

import com.darkos.kmp.feature.timer.api.model.TimerState
import com.darkos.mvu.component.ProgramComponent
import kotlinx.coroutines.flow.StateFlow

interface ITimerComponent: ProgramComponent<TimerState> {
    fun onTimerValueChanged(value: Int)
    fun onStartClick()
    fun onStopClick()
    fun onTextChanged(txt: String)
}