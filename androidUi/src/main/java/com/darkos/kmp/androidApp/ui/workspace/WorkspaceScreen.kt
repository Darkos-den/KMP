package com.darkos.kmp.androidApp.ui.workspace

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.AppToolbar
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen
import com.darkos.kmp.feature.workspace.model.WorkspaceState
import kotlinx.android.parcel.Parcelize

@Parcelize
class WorkspaceUiState : Parcelable

fun WorkspaceState.map(): WorkspaceUiState {
    return WorkspaceUiState()
}

fun WorkspaceUiState.map(): WorkspaceState {
    return WorkspaceState()
}

fun mapToWorkspaceUi(state: WorkspaceState): WorkspaceUiState {
    return state.map()
}

fun mapFromWorkspaceUi(state: WorkspaceUiState): WorkspaceState {
    return state.map()
}

@Composable
fun WorkspaceScreen(onBackClicked: () -> Unit) {
    AppToolbar(
        title = "Workspace",
        onBackClicked = onBackClicked
    ) {
        NotImplementedScreen()
    }
}