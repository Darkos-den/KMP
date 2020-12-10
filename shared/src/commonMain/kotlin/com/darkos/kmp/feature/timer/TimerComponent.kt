package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.ITimerEffectHandler
import com.darkos.kmp.feature.timer.api.ITimerReducer
import com.darkos.kmp.feature.timer.api.model.TimerMessage
import com.darkos.kmp.feature.timer.api.model.TimerState
import com.darkos.mvu.MVUComponent

class TimerComponent(
    effectHandler: ITimerEffectHandler,
    reducer: ITimerReducer
): MVUComponent<TimerState>(
    effectHandler = effectHandler,
    reducer = reducer
), ITimerComponent {

    override fun createInitialState(): TimerState {
        return TimerState(
            count = 0,
            value = 0,
            progress = false
        )
    }

    override fun onTimerValueChanged(value: Int){
        program.accept(TimerMessage.ValueChanged(value))
    }

    override fun onStartClick(){
        program.accept(TimerMessage.StartClick)
    }

    override fun onStopClick(){
        program.accept(TimerMessage.StopClick)
    }
}