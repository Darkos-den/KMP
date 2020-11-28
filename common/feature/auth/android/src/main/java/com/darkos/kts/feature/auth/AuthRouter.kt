package com.darkos.kts.feature.auth

import androidx.navigation.NavController
import com.darkos.feature.auth.R
import com.darkos.kts.feature.auth.model.AuthNavigation
import com.darkos.kts.feature.signin.model.SignInNavigation
import com.darkos.mvu.navigator.NavGraphNavigator
import com.darkos.mvu.navigator.Navigation

class AuthRouter(
    appNavigator: NavGraphNavigator
) : NavGraphNavigator() {

    init {
        appNavigator.attach(this)
    }

    override suspend fun navigate(controller: NavController, navigation: Navigation): Boolean {
        when (navigation) {
            is AuthNavigation.NavigateToSignIn -> {

            }
            is AuthNavigation.NavigateToSignUp -> {

            }
            is SignInNavigation.NavigateToSignUp -> {
                controller.navigate(R.id.action_signInFragment_to_signUpFragment)
            }
            else -> {
                return false
            }
        }

        return true
    }

    companion object {
        const val TAG = "Auth"
    }
}