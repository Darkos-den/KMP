package com.darkos.kts.initial.model.mvu

import com.darkos.core.model.navigation.Navigation

sealed class InitialNavigation: Navigation() {
    object NavigateToSplash: InitialNavigation()
}