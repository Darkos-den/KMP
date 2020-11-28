package com.darkos.kts.feature.initial.model.mvu

import com.darkos.mvu.models.Effect

sealed class InitialEffect : Effect() {
    sealed class Navigation : InitialEffect() {
        object NavigateToSplash : Navigation()
    }
}