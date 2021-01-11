package com.darkos.kmp.androidApp.ui.error.app

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AppErrorScreen(
    message: String,
    onRetry: () -> Unit,
    onRestart: () -> Unit
) {
    Column {
        Text(text = "app error: $message")
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
        Button(onClick = onRestart) {
            Text(text = "Restart")
        }
    }
}