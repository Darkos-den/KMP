package com.company.projectName.android.ui.initial

import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator
import com.darkos.core.presentation.router.ActivityRouter
import io.dynamax.android.R

class InitialRouter(
    appNavigator: Navigator
): ActivityRouter(), Navigator {

    override var subNavigators: List<Navigator> = emptyList()
    override val containerId = R.id.fragment_container

    init {
        appNavigator.attach(this)
    }

    override fun navigate(navigation: Navigation): Boolean {
        TODO("Not yet implemented")
    }
}