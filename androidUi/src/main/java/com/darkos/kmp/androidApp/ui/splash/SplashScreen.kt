package com.darkos.kmp.androidApp.ui.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.darkos.kmp.feature.splash.model.SplashState

@Composable
fun SplashScreen(
    state: SplashState,
    retryClick: () -> Unit,
    logoutClick: () -> Unit
) {
    when (state) {
        is SplashState.Progress -> {
            Text(text = "progress...")
        }
        is SplashState.ServerError -> {
            Column {
                Text(text = "Server error")
                Button(onClick = retryClick) {
                    Text(text = "Retry")
                }
                Button(onClick = logoutClick) {
                    Text(text = "Logout")
                }
            }
        }
        is SplashState.NetworkError -> {
            Text(text = "Network error")
            Button(onClick = retryClick) {
                Text(text = "Retry")
            }
        }
    }
}