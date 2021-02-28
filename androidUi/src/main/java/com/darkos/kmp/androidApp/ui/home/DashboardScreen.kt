package com.darkos.kmp.androidApp.ui.home

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.auth.signIn.FieldUiState
import com.darkos.kmp.androidApp.ui.auth.signIn.SignInUiState
import com.darkos.kmp.androidApp.ui.auth.signIn.map
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.androidApp.ui.drawer.DrawerScreen
import com.darkos.kmp.feature.dashboard.model.DashboardState
import com.darkos.kmp.feature.signin.model.ScreenState
import com.darkos.kmp.feature.signin.model.SignInState
import kotlinx.android.parcel.Parcelize

@Parcelize
class DashboardUiState : Parcelable

fun DashboardState.map(): DashboardUiState {
    return DashboardUiState()
}

fun DashboardUiState.map(): DashboardState {
    return DashboardState()
}

fun mapToDashboardUi(state: DashboardState): DashboardUiState {
    return state.map()
}

fun mapFromDashboardUi(state: DashboardUiState): DashboardState {
    return state.map()
}

@Composable
fun DashboardScreen(onLogoutClick: () -> Unit) {
    DrawerScreen(
        title = "Dashboard",
        onLogoutClick = onLogoutClick
    ) {
        NotImplementedScreen()
    }
}