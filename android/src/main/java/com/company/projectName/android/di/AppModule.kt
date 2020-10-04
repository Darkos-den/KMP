package com.company.projectName.android.di

import android.app.Application
import com.company.projectName.android.AppMessageProcessor
import com.company.projectName.android.AppNavigator
import com.company.projectName.domain.common.MessageProcessor
import com.company.projectName.domain.common.Navigator
import com.company.projectName.domain.common.SecureStorage
import com.company.projectName.domain.di.DomainModule
import com.company.projectName.domain.effectHandler.LoginEffectHandler
import com.company.projectName.domain.feature.login.LoginScreenState
import com.company.projectName.domain.feature.login.feature
import com.company.projectName.domain.repository.AuthRepository
import com.company.projectName.domain.repository.LoginRepository
import com.company.projectName.domain.repository.SampleRepository
import com.company.projectName.entity.source.remote.IAuthRemoteRepository
import com.company.projectName.entity.source.remote.ISampleRemoteRepository
import com.company.projectName.entity.source.secure.ISecureStorage
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
        bind<LoginRemote<LoginDTO,TokenDTO>>() with provider { LoginRepository() }

        import(PresentationModule.get())
        import(DomainModule.get())
        import(NavigationModule.get())

        LoginModule.get<LoginScreenState, LoginDTO, TokenDTO>(
            handlerCreator = {
                LoginEffectHandler(
                    remote = instance()
                )
            },
            reducerCreator = {
                feature
            }
        ).let {
            import(it)
        }

        bind<Navigator>() with singleton { AppNavigator() }
    }
}