package com.darkos.kmp.feature.timer.api.model

import com.darkos.mvu.model.Message

sealed class TimerMessage: Message() {
    object StartClick: TimerMessage()
    object StopClick: TimerMessage()
    class ValueChanged(val newValue: Int): TimerMessage()
}