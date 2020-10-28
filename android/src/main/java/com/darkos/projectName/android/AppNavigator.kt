package com.darkos.projectName.android

import com.darkos.core.model.navigation.Navigation
import com.darkos.core.model.navigation.Navigator

class AppNavigator : Navigator {

    override var subNavigators: List<Navigator> = emptyList()

    override fun navigate(navigation: Navigation): Boolean {
        subNavigators.forEach {
            if (it.navigate(navigation)) {
                return true
            }
        }

        throw IllegalArgumentException("navigation not supported")
    }
}