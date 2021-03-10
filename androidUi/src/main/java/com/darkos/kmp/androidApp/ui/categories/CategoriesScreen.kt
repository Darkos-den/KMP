package com.darkos.kmp.androidApp.ui.categories

import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.AppToolbar
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen

@Composable
fun CategoriesScreen(onBackClicked: () -> Unit){
    AppToolbar(
        title = "Categories",
        onBackClicked = onBackClicked
    ) {
        NotImplementedScreen()
    }
}