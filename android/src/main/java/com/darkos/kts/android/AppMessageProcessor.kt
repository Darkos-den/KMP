package com.darkos.kts.android

import android.app.Activity
import android.widget.Toast
import com.darkos.kts.domain.common.MessageProcessor

class AppMessageProcessor : MessageProcessor {

    private var activity: Activity? = null

    override fun attach(activity: Activity) {
        this.activity = activity
    }

    override fun detach() {
        activity = null
    }

    override fun showMessage(message: String) {
        activity?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}