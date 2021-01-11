package com.darkos.kmp

import com.darkos.kmp.common.alertProcessor.AlertProcessor
import com.darkos.kmp.common.alertProcessor.IAlertProcessor
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.api.ISplashSecure
import com.darkos.kmp.feature.splash.di.SplashDI
import com.darkos.kmp.feature.splash.di.SplashDiFacade
import com.darkos.kmp.source.remote.RemoteStorage
import com.darkos.kmp.source.remote.SplashRemote
import com.darkos.kmp.source.secure.SecureStorage
import com.darkos.kmp.source.secure.SplashSecure
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        import(createAppModule())
    }

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()
    fun splashDiFacade() = SplashDiFacade(diContainer.direct)

    fun createAppModule() = DI.Module("app") {
        bind() from singleton {
            ErrorHandler()
        }
        importAll(
            secureStorageModule(),
            remoteStorageModule(),
            alertProcessorModule(),
            SplashDI().module
        )
    }

    private fun secureStorageModule() = DI.Module("Source.Secure") {
        bind() from singleton {
            SecureStorage()
        }
        bind<ISplashSecure>() with provider {
            SplashSecure(
                secure = instance()
            )
        }
    }

    private fun remoteStorageModule() = DI.Module("Source.Remote") {
        bind() from singleton {
            RemoteStorage()
        }
        bind<ISplashRemote>() with provider {
            SplashRemote(
                remote = instance()
            )
        }
    }

    private fun alertProcessorModule() = DI.Module("AlertProcessor") {
        bind() from singleton {
            AlertProcessor()
        }
        bind<IAlertProcessor>() with singleton {
            instance<AlertProcessor>()
        }
    }
}