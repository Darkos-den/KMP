package com.darkos.kmp.feature.drawer.model

import com.darkos.mvu.model.MVUState

enum class DrawerDestination {
    Dashboard, Categories, Search, Profile, Contact
}

data class DrawerState(
    val currentDestination: DrawerDestination
) : MVUState()