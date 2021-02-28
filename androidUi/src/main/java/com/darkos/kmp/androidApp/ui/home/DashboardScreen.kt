package com.darkos.kmp.androidApp.ui.home

import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.androidApp.ui.drawer.DrawerScreen

@Composable
fun DashboardScreen(onLogoutClick: ()->Unit) {
    DrawerScreen(
        title = "Dashboard",
        onLogoutClick = onLogoutClick
    ) {
        NotImplementedScreen()
    }
}