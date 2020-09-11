package com.company.projectName.domain.model.effect

import com.darkos.mvu.models.Effect

internal sealed class NavigationEffect : Effect() {
    object NavigateToSplash : NavigationEffect()
    object NavigateToLogin : NavigationEffect()
    object NavigateToHome : NavigationEffect()
}