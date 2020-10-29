package com.darkos.kts.android.di

import android.app.Application
import com.darkos.core.model.navigation.Navigator
import com.darkos.core.presentation.di.PresentationModule
import com.darkos.kts.android.AppMessageProcessor
import com.darkos.kts.android.AppNavigator
import com.darkos.kts.domain.common.MessageProcessor
import com.darkos.kts.domain.common.SecureStorage
import com.darkos.kts.domain.di.DomainModule
import com.darkos.kts.entity.source.remote.IAuthRemoteRepository
import com.darkos.kts.entity.source.remote.ISampleRemoteRepository
import com.darkos.kts.entity.source.secure.ISecureStorage
import com.darkos.kts.feature.auth.AuthEffectHandler
import com.darkos.kts.feature.auth.AuthReducer
import com.darkos.kts.feature.auth.IAuthEffectHandler
import com.darkos.kts.feature.auth.IAuthReducer
import com.darkos.kts.feature.initial.IInitialEffectHandler
import com.darkos.kts.feature.initial.IInitialReducer
import com.darkos.kts.feature.initial.InitialEffectHandler
import com.darkos.kts.feature.initial.InitialReducer
import com.darkos.kts.feature.signin.LoginByEmailRemote
import com.darkos.kts.feature.splash.*
import com.darkos.kts.remote.repository.AuthRepository
import com.darkos.kts.remote.repository.LoginRemoteRepository
import com.darkos.kts.remote.repository.SampleRepository
import com.darkos.kts.secure.repository.SplashSecureRepository
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
        bind<IAuthRemoteRepository>() with provider { AuthRepository() }
        bind<ISampleRemoteRepository>() with provider { SampleRepository() }
        bind<ISecureStorage>() with provider { SecureStorage() }

        import(PresentationModule.get())
        import(DomainModule.get())
        import(NavigationModule.get())
        import(ValidationModule)

        bind<Navigator>() with singleton { AppNavigator(application) }

        bind<ISplashSecure>() with provider { SplashSecureRepository() }

        bind<LoginByEmailRemote>() with provider { LoginRemoteRepository() }

        initialFeature()
        splashFeature()
        authFeature()
    }

    private fun Kodein.Builder.authFeature() {
        bind<IAuthReducer>() with provider { AuthReducer() }
        bind<IAuthEffectHandler>() with provider {
            AuthEffectHandler(
                remote = instance(),
                validation = instance(),
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