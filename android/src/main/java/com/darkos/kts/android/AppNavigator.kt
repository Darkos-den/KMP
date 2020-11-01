package com.darkos.kts.android

import android.app.Application
import android.content.Intent
import com.darkos.core.navigation.Navigation
import com.darkos.core.navigation.Navigator
import com.darkos.kts.feature.auth.AuthActivity
import com.darkos.kts.feature.signin.model.SignInNavigation
import com.darkos.kts.feature.splash.model.SplashNavigation

class AppNavigator(
    private val application: Application
) : Navigator {

    override var subNavigators: List<Navigator> = emptyList()

    override fun navigate(navigation: Navigation): Boolean {
        when (navigation) {
            is SignInNavigation.NavigateToMain,
            is SplashNavigation.NavigateToMain -> {
                TODO()
            }
            is SplashNavigation.NavigateToLogin -> {
                Intent(application, AuthActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }.let {
                    application.startActivity(it)
                }
                return true
            }
            else -> {
                subNavigators.forEach {
                    if (it.navigate(navigation)) {
                        return true
                    }
                }
            }
        }

        throw IllegalArgumentException("navigation not supported")
    }
}