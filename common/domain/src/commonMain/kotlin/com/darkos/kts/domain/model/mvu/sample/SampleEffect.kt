package com.darkos.kts.domain.model.mvu.sample

import com.darkos.mvu.models.Effect

sealed class SampleEffect: Effect() {
    object LoadContent: SampleEffect()
}