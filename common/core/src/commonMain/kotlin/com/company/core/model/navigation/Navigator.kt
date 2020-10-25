package com.company.core.model.navigation

interface Navigator {
    var subNavigators: List<Navigator>

    fun attach(navigator: Navigator) {
        subNavigators = subNavigators + navigator
    }

    fun detach(navigator: Navigator) {
        subNavigators = subNavigators - navigator
    }

    fun navigate(navigation: Navigation): Boolean
}

abstract class Navigation