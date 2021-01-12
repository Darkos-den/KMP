package com.darkos.kmp.androidApp.ui.auth.signIn

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.darkos.kmp.androidApp.ui.common.NotImplementedScreen

@Composable
fun SignInScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "SignIn")
                }
            )
        }
    ) {
        NotImplementedScreen()
    }
}