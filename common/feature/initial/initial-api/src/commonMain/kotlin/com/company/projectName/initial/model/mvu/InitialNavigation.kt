package com.company.projectName.initial.model.mvu

import com.company.core.model.navigation.Navigation

sealed class InitialNavigation: Navigation() {
    object NavigateToSplash: InitialNavigation()
}