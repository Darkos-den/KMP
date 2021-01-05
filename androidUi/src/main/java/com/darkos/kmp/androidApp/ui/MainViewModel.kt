package com.darkos.kmp.androidApp.ui

import androidx.lifecycle.ViewModel
import com.darkos.mvu.component.ProgramComponent

class MainViewModel : ViewModel() {
    var component: ProgramComponent<*>? = null

    fun attach(component: ProgramComponent<*>) {
        this.component = component
    }

    inline fun <reified T> isValidComponent(): Boolean {
        return component?.let {
            T::class.isInstance(it)
        } ?: false
    }

    override fun onCleared() {
        whenDestroy()
        whenDestroy = {}
        component = null
        super.onCleared()
    }

    private var whenDestroy: () -> Unit = {}

    fun doWhenDestroy(block: () -> Unit) {
        whenDestroy = block
    }
}