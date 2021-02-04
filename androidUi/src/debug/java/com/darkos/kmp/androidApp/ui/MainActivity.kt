package com.darkos.kmp.androidApp.ui

import com.darkos.kmp.common.debugFeatures.DebugNotificationManager
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

@InternalCoroutinesApi
class MainActivity : CoreMainActivity() {

    override val diModule = DI.Module("Main.Debug.Di") {
        bind() from singleton {
            DebugNotificationManager()
        }
    }

    private val debugNotification: DebugNotificationManager by instance()

    override fun onResume() {
        super.onResume()
        debugNotification.attach(this)

        debugNotification.show()
    }

    override fun onPause() {
        debugNotification.hide()
        super.onPause()
    }
}
