package com.company.projectName.domain.model.effect

import com.darkos.mvu.models.Effect

sealed class SampleEffect: Effect() {
    object LoadContent: SampleEffect()
}