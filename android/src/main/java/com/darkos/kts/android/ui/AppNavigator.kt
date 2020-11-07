package com.darkos.kts.android.ui

import androidx.navigation.NavController
import com.darkos.core.navigation.BaseNavigator
import com.darkos.core.navigation.Navigation
import com.darkos.kts.R
import com.darkos.kts.feature.signin.model.SignInNavigation
import com.darkos.kts.feature.splash.model.SplashNavigation

class AppNavigator : BaseNavigator() {

    override suspend fun navigate(controller: NavController, navigation: Navigation): Boolean {
        when (navigation) {
            is SignInNavigation.NavigateToMain,
            is SplashNavigation.NavigateToMain -> {
                TODO()
            }
            is SplashNavigation.NavigateToLogin -> {
                controller.navigate(R.id.action_splashFragment_to_nav_auth)
                return true
            }
            else -> {
                childs.forEach {
                    if (it.navigate(controller, navigation)) {
                        return true
                    }
                }
            }
        }

        throw IllegalArgumentException("navigation not supported")
    }
}