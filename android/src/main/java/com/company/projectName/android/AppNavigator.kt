package com.company.projectName.android

import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator

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