package com.company.core.model.general

import com.darkos.mvu.models.Message

sealed class GeneralMessage : Message() {
    object TimerFinished : GeneralMessage()
}