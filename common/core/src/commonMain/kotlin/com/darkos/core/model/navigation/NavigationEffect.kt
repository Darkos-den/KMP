package com.darkos.core.model.navigation

import com.darkos.mvu.models.Effect

sealed class NavigationEffect : Effect() {
    object NavigateToSplash : NavigationEffect()
    object NavigateToLogin : NavigationEffect()
    object NavigateToHome : NavigationEffect()
}