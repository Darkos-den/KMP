package com.darkos.kmp.androidApp.ui

import androidx.lifecycle.ViewModel
import com.darkos.mvu.component.ProgramComponent

class MainViewModel : ViewModel() {
    var component: ProgramComponent<*>? = null
        set(value) {
            field?.clearStateListener()
            field?.clear()
            field = value
        }

    fun attach(component: ProgramComponent<*>) {
        this.component = component
    }

    inline fun <reified T> isValidComponent(): Boolean {
        return component?.let {
            T::class.isInstance(it)
        } ?: false
    }

    override fun onCleared() {
        component = null
        super.onCleared()
    }
}