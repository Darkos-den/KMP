package com.company.projectName.domain.common

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

sealed class Navigation {
    sealed class Main : Navigation() {
        object Splash : Main()
        object Login : Main()
        object Home : Main()
    }
}