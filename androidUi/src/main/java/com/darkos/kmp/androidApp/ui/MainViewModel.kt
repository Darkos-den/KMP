package com.darkos.kmp.androidApp.ui

import androidx.lifecycle.ViewModel
import com.darkos.kmp.common.attachable.Attachable
import com.darkos.mvu.component.ProgramComponent
import java.lang.ref.WeakReference

class MainViewModel : ViewModel(), Attachable<ProgramComponent<*>> {
    override var attachedObject: WeakReference<ProgramComponent<*>> = WeakReference(null)
        set(value) {
            field.get()?.clearStateListener()
            field.get()?.clear()
            field = value
        }

    val component: ProgramComponent<*>?
        get() = attachedObject.get()

    inline fun <reified T> isValidComponent(): Boolean {
        return component?.let {
            T::class.isInstance(it)
        } ?: false
    }
}