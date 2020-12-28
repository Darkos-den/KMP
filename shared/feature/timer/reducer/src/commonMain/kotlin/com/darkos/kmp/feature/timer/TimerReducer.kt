package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerReducer
import com.darkos.kmp.feature.timer.api.model.TimerEffect
import com.darkos.kmp.feature.timer.api.model.TimerMessage
import com.darkos.kmp.feature.timer.api.model.TimerState
import com.darkos.mvu.Reducer
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class TimerReducer: ITimerReducer {

    infix fun TimerState.andCmd(cmd: Effect) = StateCmdData(state = this, effect = cmd)//todo: move to core lib

    override fun update(state: TimerState, message: Message): StateCmdData<TimerState> {
        return when(message) {
            is TimerMessage.StartClick -> {
//                state.copy(
//                    progress = true,
//                    value = state.count
//                ) andCmd TimerEffect.Trigger.Start(state.count)
                state andCmd TimerEffect.NavigateToOther//todo: for check navigation
            }
            is TimerMessage.StopClick -> {
                state.copy(progress = false) andCmd TimerEffect.Trigger.Stop
            }
            is TimerMessage.Finish -> {
                state.copy(progress = false) andCmd TimerEffect.ShowSuccessMessage
            }
            is TimerMessage.TextChanged -> {
                message.value.toIntOrNull()?.let {
                    state.copy(str = message.value, count = it).none()
                }?: run {
                    if(message.value.isEmpty()) {
                        state.copy(str = message.value, count = 1)
                    }else {
                        state
                    }.none()
                }
            }
            is TimerMessage.ValueChanged -> {
                if(state.progress){
                    state.copy(value = message.newValue)
                }else{
                    state.copy(count = message.newValue)
                }.none()
            }
            is ComponentInitialized -> {
                state.none()
            }
            else -> state.none()
        }
    }
}