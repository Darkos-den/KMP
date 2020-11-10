package com.darkos.kts.feature.initial.model

import com.darkos.mvu.navigator.Navigation

sealed class InitialNavigation: Navigation() {
    object NavigateToSplash: InitialNavigation()
}