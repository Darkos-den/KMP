package com.darkos.kmp.androidApp.ui.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DrawerScreen(
    title: String,
    onLogoutClick: ()->Unit,
    block: @Composable ()->Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    Column {
                        Icon(
                            Icons.Default.Menu,
                            null,
                            modifier = Modifier.clickable { scaffoldState.drawerState.open() })
                        Button(onClick = onLogoutClick) {
                            Text("Logout")
                        }
                    }
                },
            )
        },
        drawerContent = {
            Text("Apte4ka")
        },
        scaffoldState = scaffoldState
    ) {
        block()
    }
}