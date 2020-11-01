package com.darkos.kts.feature.signin.model

import com.darkos.core.navigation.Navigation

sealed class SignInNavigation : Navigation() {
    object NavigateToMain : SignInNavigation()
}