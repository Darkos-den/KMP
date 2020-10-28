package com.darkos.projectName.initial.model.mvu

import com.darkos.core.model.navigation.Navigation

sealed class InitialNavigation: Navigation() {
    object NavigateToSplash: InitialNavigation()
}