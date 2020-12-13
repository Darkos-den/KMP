package com.darkos.kmp.androidApp

import com.darkos.kmp.feature.timer.TimerDI
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI

class AppModule {

    @InternalCoroutinesApi
    fun getModule() = DI.Module(TAG) {
        import(TimerDI().getModule())
    }

    companion object {
        private const val TAG = "App"
    }
}