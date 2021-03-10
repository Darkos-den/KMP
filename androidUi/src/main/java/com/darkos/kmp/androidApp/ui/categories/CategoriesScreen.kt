package com.darkos.kmp.androidApp.ui.categories

import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.AppToolbar
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.androidApp.ui.drawer.DrawerScreen
import com.darkos.kmp.feature.drawer.model.DrawerDestination

@Composable
fun CategoriesScreen(
    onLogoutClick: () -> Unit,
    onDashboardClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onContactClick: () -> Unit,
){
    DrawerScreen(
        title = "Categories",
        currentDestination = DrawerDestination.Categories,
        onLogoutClick = onLogoutClick,
        onDashboardClick = onDashboardClick,
        onCategoriesClick = onCategoriesClick,
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick,
        onContactClick = onContactClick
    ) {
        NotImplementedScreen()
    }
}