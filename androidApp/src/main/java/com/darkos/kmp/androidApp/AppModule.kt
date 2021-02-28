package com.darkos.kmp.androidApp

import com.darkos.kmp.CommonInjector
import com.darkos.kmp.common.auth.AuthManager
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider
import org.kodein.di.singleton

class AppModule {

    @InternalCoroutinesApi
    fun getModule() = DI.Module(TAG) {
        import(CommonInjector.createAppModule())
        bind<AuthManager>() with singleton { GoogleAuthManager() }
    }

    companion object {
        private const val TAG = "App"
    }
}