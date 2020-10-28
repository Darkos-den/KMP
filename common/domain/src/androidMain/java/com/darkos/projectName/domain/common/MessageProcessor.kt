package com.darkos.projectName.domain.common

import android.app.Activity

actual interface MessageProcessor {
    actual fun showMessage(message: String)
    fun attach(activity: Activity)
    fun detach()
}