package com.company.projectName.domain.di

import com.company.projectName.domain.effectHandler.*
import com.company.projectName.domain.effectHandler.AppEffectHandler
import com.company.projectName.domain.effectHandler.GeneralEffectHandler
import com.company.projectName.domain.effectHandler.NavigationEffectHandler
import com.darkos.mvu.EffectHandler
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object DomainModule {
    fun get() = Kodein.Module("Domain") {
        bind<EffectHandler>() with singleton {
            AppEffectHandler(
                generalEffectHandler = GeneralEffectHandler(
                    messageProcessor = instance()
                ),
                navigationEffectHandler = NavigationEffectHandler(
                    navigator = instance()
                ),
                authEffectHandler = AuthEffectHandler(
                    remote = instance(),
                    secure = instance()
                ),
                sampleEffectHandler = SampleEffectHandler(
                    remote = instance()
                )
            )
        }
    }
}