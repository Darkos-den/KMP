package com.darkos.kts.feature.auth.model

import com.darkos.core.navigation.Navigation

sealed class AuthNavigation: Navigation() {
    object NavigateToSignIn: AuthNavigation()
    object NavigateToSignUp: AuthNavigation()
}