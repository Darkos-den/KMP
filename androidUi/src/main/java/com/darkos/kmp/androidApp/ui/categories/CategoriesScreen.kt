package com.darkos.kmp.androidApp.ui.categories

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.AppToolbar
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.androidApp.ui.drawer.DrawerScreen
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.kmp.feature.drawer.model.DrawerDestination
import com.darkos.kmp.feature.item.categories.model.CategoriesState
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoriesUiState : Parcelable

fun CategoriesState.map(): CategoriesUiState {
    return CategoriesUiState()
}

fun CategoriesUiState.map(): CategoriesState {
    return CategoriesState()
}

fun mapToCategoriesUi(state: CategoriesState): CategoriesUiState {
    return state.map()
}

fun mapFromCategoriesUi(state: CategoriesUiState): CategoriesState {
    return state.map()
}

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