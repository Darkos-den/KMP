package com.darkos.kmp.feature.timer.api.model

import com.darkos.mvu.model.Message
import com.darkos.mvu.model.flow.FinalMessage

sealed class TimerMessage: Message() {
    object StartClick: TimerMessage()
    object StopClick: TimerMessage()

    class ValueChanged(val newValue: Int): TimerMessage()//todo: refactor
    object Finish: TimerMessage(), FinalMessage

    class TextChanged(val value: String): TimerMessage()
}