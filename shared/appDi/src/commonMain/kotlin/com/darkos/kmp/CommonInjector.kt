package com.darkos.kmp

import com.darkos.kmp.common.alertProcessor.AlertProcessor
import com.darkos.kmp.common.alertProcessor.IAlertProcessor
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.feature.signin.api.ISignInRemote
import com.darkos.kmp.feature.signin.api.ISignInSecure
import com.darkos.kmp.feature.signin.di.SignInDI
import com.darkos.kmp.feature.splash.api.ISplashNavigation
import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.api.ISplashSecure
import com.darkos.kmp.feature.splash.di.SplashDI
import com.darkos.kmp.feature.splash.di.SplashDiFacade
import com.darkos.kmp.source.remote.RemoteStorage
import com.darkos.kmp.source.remote.SignInRemote
import com.darkos.kmp.source.remote.SplashRemote
import com.darkos.kmp.source.secure.SignInSecure
import com.darkos.kmp.source.secure.SplashSecure
import com.darkos.kmp.source.secure.common.SecureStorage
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CommonInjector {

    private val diContainer by DI.lazy {
        import(createAppModule())
    }

    fun getAlertProcessor(): AlertProcessor = diContainer.direct.instance()
    fun splashDiFacade() = SplashDiFacade(diContainer.direct)
    fun getNavigator(): CommonNavigator = diContainer.direct.instance()

    fun createAppModule() = DI.Module("app") {
        bind() from singleton {
            ErrorHandler()
        }
        bind() from singleton {
            CommonNavigator()
        }

        bind<ISplashNavigation>() with provider {
            instance<CommonNavigator>()
        }

        importAll(
            secureStorageModule(),
            remoteStorageModule(),
            alertProcessorModule(),
            SplashDI().module,
            SignInDI().module
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
        bind<ISignInSecure>() with provider {
            SignInSecure(
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
        bind<ISignInRemote>() with provider {
            SignInRemote(
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