package com.darkos.kmp.feature.drawer.model

import com.darkos.mvu.model.Message

sealed class DrawerMessage : Message() {
    object LogoutClicked: DrawerMessage()
}