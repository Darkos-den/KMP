package com.darkos.kmp.feature.drawer.model

import com.darkos.mvu.model.Effect

sealed class DrawerEffect : Effect(){
    object Logout: DrawerEffect()

    object OpenDashboard: DrawerEffect()
    object OpenCategories: DrawerEffect()
    object OpenSearch: DrawerEffect()
    object OpenProfile: DrawerEffect()
    object OpenContacts: DrawerEffect()
}