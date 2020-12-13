package com.darkos.kmp.androidApp

import android.app.Application
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger

class App: Application(), DIAware {

    @InternalCoroutinesApi
    override val di: DI = DI.lazy {
        import(AppModule().getModule())
    }

    override val diTrigger = DITrigger()

    override fun onCreate() {
        super.onCreate()
        diTrigger.trigger()
    }
}