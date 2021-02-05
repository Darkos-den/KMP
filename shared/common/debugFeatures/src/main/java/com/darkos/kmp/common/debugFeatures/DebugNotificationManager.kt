package com.darkos.kmp.common.debugFeatures

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.darkos.kmp.common.attachable.Attachable
import java.lang.ref.WeakReference

class DebugNotificationManager : Attachable<Context> {
    override var attachedObject: WeakReference<Context> = WeakReference(null)

    private val context: Context?
        get() = attachedObject.get()

    private val manager: NotificationManager?
        get() = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    fun show() {
        context?.let {
            createChannel(it)

            val intent = Intent(it, DebugActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            val pending = PendingIntent.getActivity(it, 0, intent, 0)

            val builder = Notification.Builder(it, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.btn_default_small)
                .setContentTitle("Debug mode")
                .setContentText("Open debug console")
                .setContentIntent(pending)
                .setAutoCancel(true)

            manager?.notify(NOTIFICATION_ID, builder.build())
        }
    }

    fun hide() {
        manager?.cancel(NOTIFICATION_ID)
    }

    private fun createChannel(context: Context) {
        val name = "Debug mode"
        val descriptionText = "Debug mode notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "App.Debug"
        private const val NOTIFICATION_ID = 123
    }
}