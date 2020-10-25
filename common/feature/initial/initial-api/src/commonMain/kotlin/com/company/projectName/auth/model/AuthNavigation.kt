package com.company.projectName.auth.model

import com.company.core.model.navigation.Navigation

sealed class AuthNavigation: Navigation() {
    object NavigateToSignIn: AuthNavigation()
    object NavigateToSignUp: AuthNavigation()
}