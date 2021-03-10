package com.darkos.kmp.androidApp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppToolbar(
    title: String,
    onBackClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        null,
                        modifier = Modifier.padding(start = 12.dp)
                            .clickable { onBackClicked() }
                    )
                }
            )
        }
    ) {
        content()
    }
}