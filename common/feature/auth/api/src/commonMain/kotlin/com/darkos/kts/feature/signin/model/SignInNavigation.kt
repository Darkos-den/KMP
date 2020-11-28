package com.darkos.kts.feature.signin.model

import com.darkos.mvu.navigator.Navigation

sealed class SignInNavigation : Navigation() {
    object NavigateToMain : SignInNavigation()
    object NavigateToSignUp : SignInNavigation()
}