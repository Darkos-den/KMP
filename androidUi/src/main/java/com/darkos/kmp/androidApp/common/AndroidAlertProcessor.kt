package com.darkos.kmp.androidApp.common

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.darkos.kmp.alertProcessor.AlertProcessor
import java.lang.ref.WeakReference

class AndroidAlertProcessor(
    alertProcessor: AlertProcessor
) {
    init {
        alertProcessor.showSimpleMessage = { message ->
            activity.get()?.let {
                AlertDialog.Builder(it)
                    .setMessage(message)
                    .setPositiveButton("OK") { d, _ ->
                        d.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    private var activity = WeakReference<AppCompatActivity>(null)

    fun attach(activity: AppCompatActivity) {
        this.activity = WeakReference(activity)
    }
}