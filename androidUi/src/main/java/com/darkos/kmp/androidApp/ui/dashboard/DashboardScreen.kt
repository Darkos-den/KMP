package com.darkos.kmp.androidApp.ui.dashboard

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.androidApp.ui.drawer.DrawerDestination
import com.darkos.kmp.androidApp.ui.drawer.DrawerScreen
import com.darkos.kmp.feature.dashboard.model.DashboardState
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
fun DashboardScreen(
    onLogoutClick: () -> Unit,
    onAddClick: ()->Unit,
    onSettingsClick: ()->Unit
) {
    DrawerScreen(
        title = "Dashboard",
        onLogoutClick = onLogoutClick,
        actions = {
            Icon(
                Icons.Filled.Settings,
                "Settings",
                tint = Color.White,
                modifier = Modifier.padding(end = 12.dp).clickable {
                    onSettingsClick()
                }
            )
        },
        currentDestination = DrawerDestination.Dashboard
    ) {
        Box {
            NotImplementedScreen()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                FloatingActionButton(
                    onClick = onAddClick,
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, null)
                }
            }
        }
    }
}