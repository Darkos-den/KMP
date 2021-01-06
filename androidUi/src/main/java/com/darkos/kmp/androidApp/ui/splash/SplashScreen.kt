package com.darkos.kmp.androidApp.ui.splash

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.darkos.kmp.feature.splash.model.SplashState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SplashUiState(
    val count: Int
) : Parcelable

fun SplashState.map(): SplashUiState {
    return SplashUiState(count)
}

fun SplashUiState.map(): SplashState {
    return SplashState(count)
}

@Composable
fun SplashScreen(
    state: SplashUiState,
    onPlus: () -> Unit,
    onNext: () -> Unit
) {
    Column {
        Text(text = "progress... ${state.count}")
        Button(onClick = onPlus) {
            Text(text = "Plus")
        }
        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }
}