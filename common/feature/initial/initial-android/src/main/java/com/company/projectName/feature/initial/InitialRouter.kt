package com.company.projectName.feature.initial

import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator
import com.company.projectName.auth.model.AuthNavigation
import com.company.projectName.feature.initial.splash.SplashFragment
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.feature.initial.R

class InitialRouter(
    appNavigator: Navigator
): ActivityRouter(), Navigator {

    override val containerId = R.id.fragmentContainer
    override var subNavigators: List<Navigator> = emptyList()

    init {
        appNavigator.attach(this)
    }

    override fun navigate(navigation: Navigation): Boolean {
        if (navigation !is AuthNavigation) {
            return false
        }

        when (navigation) {
            is AuthNavigation.NavigateToSignIn -> createInstance<SplashFragment>().replace()
        }

        return true
    }
}