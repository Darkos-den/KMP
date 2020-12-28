package com.darkos.kmp.androidApp.common

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore

typealias ComposeView = @Composable (ViewModel)->Unit

class AndroidRouter(
    initialRoute: Route
) {
    private var backStack: List<Route> = listOf(initialRoute)
        set(value) {
            field = value
            current = value.last()
        }

    var current by mutableStateOf(initialRoute)
}

abstract class Route: ViewModelStore(){
    abstract val view: ComposeView
    abstract val viewModel: ViewModel

    @Composable
    fun View(){
        view.invoke(viewModel)
    }
}