package com.darkos.kmp.androidApp

import com.darkos.kmp.CommonInjector
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI

class AppModule {

    @InternalCoroutinesApi
    fun getModule() = DI.Module(TAG) {
        import(CommonInjector.createAppModule())
    }

    companion object {
        private const val TAG = "App"
    }
}