package com.darkos.kmp.androidApp.common

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.darkos.kmp.common.alertProcessor.AlertProcessor
import com.darkos.kmp.common.attachable.Attachable
import java.lang.ref.WeakReference

class AndroidAlertProcessor(
    alertProcessor: AlertProcessor
) : Attachable<AppCompatActivity> {
    init {
        alertProcessor.showSimpleMessage = { message ->
            attachedObject.get()?.let {
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

    override var attachedObject: WeakReference<AppCompatActivity> =
        WeakReference<AppCompatActivity>(null)
}