package com.darkos.kmp.androidApp

import android.app.Application
import com.darkos.kmp.source.remote.RemoteStorage
import com.darkos.kmp.source.secure.common.SecureStorage
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.instance

class App : Application(), DIAware {

    @InternalCoroutinesApi
    override val di: DI = DI.lazy {
        import(AppModule().getModule())
    }

    private val secure: SecureStorage by instance()
    private val remote: RemoteStorage by instance()

    override val diTrigger = DITrigger()

    override fun onCreate() {
        super.onCreate()
        diTrigger.trigger()

        secure.init()

        remote.configure {
            apiVersion = 1
            environment = RemoteStorage.Config.Environment.DEVELOPMENT
        }
    }
}