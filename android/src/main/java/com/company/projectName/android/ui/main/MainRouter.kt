package com.company.projectName.android.ui.main

import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator
import com.darkos.core.presentation.router.ActivityRouter
import io.dynamax.android.R

class MainRouter(
    appNavigator: Navigator
) : ActivityRouter(), Navigator {

    override var subNavigators: List<Navigator> = emptyList()
    override val containerId = R.id.fragment_container

    init {
        appNavigator.attach(this)
    }

    override fun navigate(navigation: Navigation): Boolean {
//        if (navigation !is Navigation.Main) {
//            return false
//        }
//
//        when (navigation) {
//            is Navigation.Main.Splash -> createInstance<SplashFragment>().replace()
//            is Navigation.Main.Login -> createInstance<LoginFragment>().replace()
//            is Navigation.Main.Home -> createInstance<HomeFragment>().replace()
//        }

        return true
    }
}