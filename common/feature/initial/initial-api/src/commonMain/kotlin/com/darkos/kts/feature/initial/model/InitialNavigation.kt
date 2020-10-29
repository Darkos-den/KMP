package com.darkos.kts.feature.initial.model

import com.darkos.core.navigation.Navigation

sealed class InitialNavigation: Navigation() {
    object NavigateToSplash: InitialNavigation()
}