package com.company.projectName.domain.model.mvu.general

import com.darkos.mvu.models.Message

internal sealed class GeneralMessage : Message() {
    object TimerFinished : GeneralMessage()
}