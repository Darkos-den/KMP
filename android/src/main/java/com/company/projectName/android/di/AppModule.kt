package com.company.projectName.android.di

import android.app.Application
import com.company.projectName.android.AppMessageProcessor
import com.company.projectName.android.AppNavigator
import com.company.projectName.domain.common.MessageProcessor
import com.company.projectName.domain.common.Navigator
import com.company.projectName.domain.di.DomainModule
import com.company.projectName.domain.repository.AuthRepository
import com.company.projectName.domain.repository.SampleRepository
import com.company.projectName.entity.source.IAuthRemoteRepository
import com.company.projectName.entity.source.ISampleRemoteRepository
import com.darkos.core.presentation.di.PresentationModule
import org.kodein.di.Kodein
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object AppModule {
    fun get(application: Application) = Kodein.Module("App") {
        androidCoreModule(application)

        bind<MessageProcessor>() with singleton { AppMessageProcessor() }
        bind<IAuthRemoteRepository>() with provider { AuthRepository() }
        bind<ISampleRemoteRepository>() with provider { SampleRepository() }

        import(PresentationModule.get())
        import(DomainModule.get())
        import(NavigationModule.get())

        bind<Navigator>() with singleton { AppNavigator() }
    }
}