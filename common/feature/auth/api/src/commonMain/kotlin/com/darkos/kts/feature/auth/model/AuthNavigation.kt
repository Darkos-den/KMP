package com.darkos.kts.feature.auth.model

import com.darkos.mvu.navigator.Navigation

sealed class AuthNavigation: Navigation() {
    object NavigateToSignIn: AuthNavigation()
    object NavigateToSignUp: AuthNavigation()
}