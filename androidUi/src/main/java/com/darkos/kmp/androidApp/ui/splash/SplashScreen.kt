package com.darkos.kmp.androidApp.ui.splash

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.darkos.kmp.feature.splash.model.SplashState
import kotlinx.android.parcel.Parcelize

enum class SplashScreenState {
    INIT, PROGRESS, ERROR
}

@Parcelize
data class SplashUiState(
    val screenState: SplashScreenState
) : Parcelable

fun SplashState.map(): SplashUiState {
    return when (this) {
        is SplashState.Init -> SplashUiState(SplashScreenState.INIT)
        is SplashState.PrepareData -> SplashUiState(SplashScreenState.PROGRESS)
        is SplashState.RefreshTokenError -> SplashUiState(SplashScreenState.ERROR)
    }
}

fun mapToSplashUi(state: SplashState): SplashUiState {
    return state.map()
}

fun mapFromSplashUi(state: SplashUiState): SplashState {
    return state.map()
}

fun SplashUiState.map(): SplashState {
    return when (screenState) {
        SplashScreenState.INIT -> SplashState.Init
        SplashScreenState.PROGRESS -> SplashState.PrepareData
        SplashScreenState.ERROR -> SplashState.RefreshTokenError
    }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "progress...")
    }
}