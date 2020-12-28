package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.INavigator
import com.darkos.kmp.feature.timer.api.ITimerEffectHandler
import com.darkos.kmp.feature.timer.api.alertProcessor.AlertProcessor
import com.darkos.kmp.feature.timer.api.alertProcessor.IAlertProcessor
import com.darkos.kmp.feature.timer.api.model.TimerEffect
import com.darkos.kmp.feature.timer.api.model.TimerMessage
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.flow.FlowEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimerEffectHandler(
    private val alertProcessor: IAlertProcessor,
    private val navigator: INavigator
): ITimerEffectHandler {

    private fun Effect.notValid() : IllegalArgumentException {//todo: move to core lib
        return IllegalArgumentException("not valid effect: ${this::class.simpleName}")
    }

    override suspend fun call(effect: Effect): Message {
        return when(effect){
            is TimerEffect.Trigger.Stop -> Idle
            is TimerEffect.ShowSuccessMessage -> {
                alertProcessor.showSimpleMessage("timer finished")
                Idle
            }
            is TimerEffect.NavigateToOther -> {
                navigator.navigateToOtherTimer()
                Idle
            }
            else -> throw effect.notValid()
        }
    }

    override suspend fun <T> callAsFlow(effect: T): Flow<Message> where T : Effect, T : FlowEffect {
        return when(effect) {
            is TimerEffect.Trigger.Start -> {
                flow<Message> {
                    for(i in 1..effect.value){
                        delay(1_000)//todo: use const
                        emit(TimerMessage.ValueChanged(effect.value - i))
                    }
                    delay(1_000)
                    emit(TimerMessage.Finish)
                }
            }
            else -> {
                throw effect.notValid()
            }
        }
    }

}