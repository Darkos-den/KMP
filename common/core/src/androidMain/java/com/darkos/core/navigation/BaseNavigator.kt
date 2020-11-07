package com.darkos.core.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

abstract class BaseNavigator : Navigator {

    private var subNavigators: List<WeakReference<BaseNavigator>> = emptyList()
        get() {
            field = field.filter {
                it.get() != null
            }
            return field
        }
    val childs: List<BaseNavigator>
        get() = subNavigators.mapNotNull { it.get() }

    private var controller: WeakReference<NavController> = WeakReference(null)

    fun attach(navigator: BaseNavigator) {
        subNavigators = subNavigators + WeakReference(navigator)
    }

    fun attachNavController(controller: NavController) {
        this.controller = WeakReference(controller)
    }

    override suspend fun navigate(navigation: Navigation): Boolean {
        return withContext(Dispatchers.Main) {
            controller.get()?.let {
                navigate(it, navigation)
            } ?: throw IllegalStateException(
                "controller not available"
            )
        }
    }

    abstract suspend fun navigate(controller: NavController, navigation: Navigation): Boolean
}