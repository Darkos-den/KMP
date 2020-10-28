package com.darkos.core.model.general

import com.darkos.mvu.models.Effect

sealed class GeneralEffect: Effect() {
    class Timer(val delay: Long): GeneralEffect()
    class ShowUserMessage(val message: String): GeneralEffect()
}