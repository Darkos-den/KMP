package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerReducer
import com.darkos.kmp.feature.timer.api.model.TimerEffect
import com.darkos.kmp.feature.timer.api.model.TimerMessage
import com.darkos.kmp.feature.timer.api.model.TimerState
import com.darkos.mvu.Reducer
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class TimerReducer: ITimerReducer {

    override fun update(state: TimerState, message: Message): StateCmdData<TimerState> {
        return when(message) {
            is TimerMessage.StartClick -> {
                StateCmdData(
                    state = state.copy(progress = true, value = state.count),
                    effect = TimerEffect.Trigger.Start(state.count)
                )
            }
            is TimerMessage.StopClick -> {
                StateCmdData(
                    state = state.copy(progress = false),
                    effect = TimerEffect.Trigger.Stop
                )
            }
            is TimerMessage.Finish -> {
                state.copy(progress = false).none()
            }
            is TimerMessage.TextChanged -> {
                state.copy(str = message.value).none()
            }
            is TimerMessage.ValueChanged -> {
                if(state.progress){
                    state.copy(value = message.newValue)
                }else{
                    state.copy(count = message.newValue)
                }.none()
            }
            is ComponentInitialized -> {
                state.copy(count = 15).none()
            }
            else -> state.none()
        }
    }
}