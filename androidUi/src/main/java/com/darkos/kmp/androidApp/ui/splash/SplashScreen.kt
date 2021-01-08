package com.darkos.kmp.androidApp.ui.splash

import android.os.Parcelable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    return SplashState(count)
}

@Composable
fun SplashScreen() {
    Text(text = "progress...")
}