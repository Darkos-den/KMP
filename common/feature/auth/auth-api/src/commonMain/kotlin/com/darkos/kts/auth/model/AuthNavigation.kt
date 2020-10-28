package com.darkos.kts.auth.model

import com.darkos.core.model.navigation.Navigation

sealed class AuthNavigation: Navigation() {
    object NavigateToSignIn: AuthNavigation()
    object NavigateToSignUp: AuthNavigation()
}