package com.darkos.kts.feature.auth

import androidx.navigation.NavController
import com.darkos.core.navigation.BaseNavigator
import com.darkos.core.navigation.Navigation
import com.darkos.feature.auth.R
import com.darkos.kts.feature.auth.model.AuthNavigation
import com.darkos.kts.feature.signin.model.SignInNavigation

class AuthRouter(
    appNavigator: BaseNavigator
) : BaseNavigator() {

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