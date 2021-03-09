package com.darkos.kmp.androidApp.ui.home

import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
fun DashboardScreen(
    onLogoutClick: () -> Unit,
    onAddClick: ()->Unit
) {
    DrawerScreen(
        title = "Dashboard",
        onLogoutClick = onLogoutClick
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