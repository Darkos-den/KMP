package com.darkos.kmp.feature.drawer.model

import com.darkos.mvu.model.Message

sealed class DrawerMessage : Message() {
    object LogoutClicked: DrawerMessage()
    object DashboardClicked: DrawerMessage()
    object CategoriesClicked: DrawerMessage()
    object SearchClicked: DrawerMessage()
    object ProfileClicked: DrawerMessage()
    object ContactClicked: DrawerMessage()
}