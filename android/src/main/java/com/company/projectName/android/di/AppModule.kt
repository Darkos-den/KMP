package com.company.projectName.android.di

import android.app.Application
import com.company.projectName.android.AppMessageProcessor
import com.company.projectName.android.AppNavigator
import com.company.projectName.domain.common.MessageProcessor
import com.company.core.model.navigation.Navigator
import com.company.projectName.domain.common.SecureStorage
import com.company.projectName.domain.di.DomainModule
import com.company.projectName.domain.repository.AuthRepository
import com.company.projectName.domain.repository.SampleRepository
import com.company.projectName.entity.source.remote.IAuthRemoteRepository
import com.company.projectName.entity.source.remote.ISampleRemoteRepository
import com.company.projectName.entity.source.secure.ISecureStorage
import com.company.projectName.initial.*
import com.company.projectName.initial.splash.ISplashEffectHandler
import com.company.projectName.initial.splash.ISplashReducer
import com.darkos.core.presentation.di.PresentationModule
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

        bind<Navigator>() with singleton { AppNavigator() }

        initialFeature()
        splashFeature()
    }

    private fun Kodein.Builder.splashFeature() {
        bind<ISplashReducer>() with provider { SplashReducer() }
        bind<ISplashEffectHandler>() with provider {
            SplashEffectHandler()
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