package com.darkos.kts.android.di

import android.app.Application
import com.darkos.core.messageProcessor.MessageProcessor
import com.darkos.core.navigation.BaseNavigator
import com.darkos.core.presentation.di.PresentationModule
import com.darkos.kts.android.AppMessageProcessor
import com.darkos.kts.android.ui.AppNavigator
import com.darkos.kts.feature.auth.*
import com.darkos.kts.feature.initial.IInitialEffectHandler
import com.darkos.kts.feature.initial.IInitialReducer
import com.darkos.kts.feature.initial.InitialEffectHandler
import com.darkos.kts.feature.initial.InitialReducer
import com.darkos.kts.feature.signin.*
import com.darkos.kts.feature.splash.*
import com.darkos.kts.remote.repository.LoginRemoteRepository
import com.darkos.kts.secure.repository.SecureRepository
import com.darkos.kts.secure.repository.base.SecureStorage
import com.darkos.validation.di.ValidationModule
import org.kodein.di.Kodein
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object AppModule {
    fun get(application: Application) = Kodein.Module("App") {
        androidCoreModule(application)

        bind<MessageProcessor>() with singleton { AppMessageProcessor() }

        bind() from singleton {
            SecureStorage().apply {
                init(application)
            }
        }

        import(PresentationModule.get())
        import(NavigationModule.get())
        import(ValidationModule)

        bind<BaseNavigator>() with singleton { AppNavigator() }

        initialFeature()
        splashFeature()
        authFeature()
        signInFeature()
    }

    private fun Kodein.Builder.signInFeature() {
        bind<ISignInReducer>() with provider { SignInReducer() }
        bind<ISignInEffectHandler>() with provider {
            SignInEffectHandler(
                navigator = instance(),
                remote = instance(),
                validation = instance(),
                secure = instance(),
                messageProcessor = instance()
            )
        }
        bind<LoginByEmailRemote>() with provider { LoginRemoteRepository() }
        bind<ISignInSecure>() with provider { SecureRepository(instance()) }
    }

    private fun Kodein.Builder.authFeature() {
        bind<IAuthReducer>() with provider { AuthReducer() }
        bind<IAuthEffectHandler>() with provider {
            AuthEffectHandler(
                navigator = instance()
            )
        }
    }

    private fun Kodein.Builder.splashFeature() {
        bind<ISplashReducer>() with provider { SplashReducer() }
        bind<ISplashEffectHandler>() with provider {
            SplashEffectHandler(
                secure = instance(),
                navigator = instance()
            )
        }
        bind<ISplashSecure>() with provider { SecureRepository(instance()) }
    }

    private fun Kodein.Builder.initialFeature() {
        bind<IInitialReducer>() with provider { InitialReducer() }
        bind<IInitialEffectHandler>() with provider {
            InitialEffectHandler(
                navigator = instance()
            )
        }
    }
}