package com.company.projectName.domain.model.effect

import com.darkos.mvu.models.Effect

internal sealed class GeneralEffect: Effect() {
    class Timer(val delay: Long): GeneralEffect()
    class ShowUserMessage(val message: String): GeneralEffect()
}