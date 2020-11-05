package com.darkos.kts.feature.initial

import com.darkos.core.navigation.Navigation
import com.darkos.core.navigation.Navigator
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.feature.initial.R
import com.darkos.kts.feature.initial.model.InitialNavigation
import com.darkos.kts.feature.splash.SplashFragment

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