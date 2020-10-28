package com.darkos.projectName.initial.model.mvu

import com.darkos.mvu.models.Effect

sealed class InitialEffect : Effect() {
    sealed class Navigation : InitialEffect() {
        object NavigateToSplash : Navigation()
    }
}