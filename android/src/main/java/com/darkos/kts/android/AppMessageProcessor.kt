package com.darkos.kts.android

import android.app.Activity
import android.widget.Toast
import com.darkos.core.messageProcessor.MessageProcessor

class AppMessageProcessor : MessageProcessor {

    private var activity: Activity? = null

    override fun attach(activity: Activity) {
        this.activity = activity
    }

    override fun detach(activity: Activity) {
        if (this.activity == activity) {
            this.activity = null
        }
    }

    override fun showMessage(message: String) {
        activity?.let {
            it.runOnUiThread {
                Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}