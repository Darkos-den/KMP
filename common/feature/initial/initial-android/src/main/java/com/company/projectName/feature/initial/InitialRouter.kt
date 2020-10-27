package com.company.projectName.feature.initial

import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator
import com.company.projectName.feature.initial.splash.SplashFragment
import com.company.projectName.initial.model.mvu.InitialNavigation
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.feature.initial.R

class InitialRouter(
    appNavigator: Navigator
): ActivityRouter(), Navigator {

    override val containerId = R.id.initialFragmentContainer
    override var subNavigators: List<Navigator> = emptyList()

    init {
        appNavigator.attach(this)
    }

    override fun navigate(navigation: Navigation): Boolean {
        if (navigation !is InitialNavigation) {
            return false
        }

        when (navigation) {
            is InitialNavigation.NavigateToSplash -> createInstance<SplashFragment>().replace()
        }

        return true
    }
}