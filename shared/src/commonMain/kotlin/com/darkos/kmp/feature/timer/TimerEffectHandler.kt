package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerEffectHandler
import com.darkos.kmp.feature.timer.api.model.TimerEffect
import com.darkos.kmp.feature.timer.api.model.TimerMessage
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.flow.FlowEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

class TimerEffectHandler: ITimerEffectHandler{

    private fun Effect.notValid() : IllegalArgumentException {
        return IllegalArgumentException("not valid effect: ${this::class.simpleName}")
    }

    override suspend fun call(effect: Effect): Message {
        throw effect.notValid()
    }

    override suspend fun <T> callAsFlow(effect: T): Flow<Message> where T : Effect, T : FlowEffect {
        return when(effect) {
            is TimerEffect.Trigger.Start -> {
                flow<Message> {
                    for(i in 1..effect.value){
                        delay(1000)
                        emit(TimerMessage.ValueChanged(effect.value - i))
                    }
                }
            }
            else -> {
                throw effect.notValid()
            }
        }
    }

}