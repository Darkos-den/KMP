package com.darkos.kmp.androidApp.ui.error.connection

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ConnectionErrorScreen(
    onRetry: () -> Unit
) {
    Column {
        Text(text = "Connection error")
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}