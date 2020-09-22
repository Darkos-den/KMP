package com.company.projectName.domain.model.mvu.sample

import com.darkos.mvu.models.Effect

sealed class SampleEffect: Effect() {
    object LoadContent: SampleEffect()
}