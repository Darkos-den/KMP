package com.darkos.kmp.common.attachable

import java.lang.ref.WeakReference

interface Attachable<T : Any> {
    var attachedObject: WeakReference<T>

    fun attach(obj: T) {
        this.attachedObject = WeakReference(obj)
    }

    fun clear() {
        attachedObject.clear()
    }
}