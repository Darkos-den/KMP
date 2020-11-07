package com.darkos.kts.android

import android.app.Activity
import android.widget.Toast
import com.darkos.core.messageProcessor.MessageProcessor
import java.lang.ref.WeakReference

class AppMessageProcessor : MessageProcessor {

    private var activityRef = WeakReference<Activity>(null)

    override fun attach(activity: Activity) {
        activityRef = WeakReference(activity)
    }

    override fun showMessage(message: String) {
        activityRef.get()?.let {
            it.runOnUiThread {
                Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}