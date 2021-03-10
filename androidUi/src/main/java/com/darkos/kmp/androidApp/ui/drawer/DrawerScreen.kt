package com.darkos.kmp.androidApp.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkos.kmp.feature.drawer.model.DrawerDestination

@Composable
fun MenuItem(text: String, selected: Boolean, onClick: () -> Unit) {
    val selectedState = Modifier.fillMaxWidth()
        .background(Color.LightGray).padding(8.dp)
    val unselectedState = Modifier.fillMaxWidth()
        .padding(8.dp)

    val current = if (selected) selectedState else unselectedState

    Row(current.clickable { onClick() }) {
        Icon(Icons.Filled.Menu, text)
        Text(
            text,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun DrawerScreen(
    title: String,
    currentDestination: DrawerDestination,
    onLogoutClick: () -> Unit,
    onDashboardClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onContactClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    block: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    Icon(
                        Icons.Default.Menu,
                        null,
                        modifier = Modifier.padding(start = 12.dp)
                            .clickable { scaffoldState.drawerState.open() }
                    )
                },
                actions = actions
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        Modifier.fillMaxWidth()
                            .height(128.dp)
                            .background(Color.Blue),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Apte4ka", fontSize = 48.sp, color = Color.White)
                    }
                    Spacer(Modifier.height(8.dp))
                    MenuItem(
                        "Dashboard",
                        currentDestination == DrawerDestination.Dashboard,
                        onDashboardClick
                    )
                    MenuItem(
                        "Categories",
                        currentDestination == DrawerDestination.Categories,
                        onCategoriesClick
                    )
                    MenuItem(
                        "Search",
                        currentDestination == DrawerDestination.Search,
                        onSearchClick
                    )
                    MenuItem(
                        "Profile",
                        currentDestination == DrawerDestination.Profile,
                        onProfileClick
                    )

                    MenuItem(
                        "Contact",
                        currentDestination == DrawerDestination.Contact,
                        onContactClick
                    )
                }

                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Text("Logout")
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        block()
    }
}