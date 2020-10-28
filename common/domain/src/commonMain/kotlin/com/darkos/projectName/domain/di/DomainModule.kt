package com.darkos.projectName.domain.di

import com.darkos.projectName.domain.effectHandler.*
import com.darkos.projectName.domain.effectHandler.AppEffectHandler
import com.darkos.projectName.domain.effectHandler.NavigationEffectHandler
import com.darkos.mvu.EffectHandler
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object DomainModule {
    fun get() = Kodein.Module("Domain") {
        bind<EffectHandler>() with singleton {
            AppEffectHandler(
                navigationEffectHandler = NavigationEffectHandler(
                    navigator = instance()
                ),
                sampleEffectHandler = SampleEffectHandler(
                    remote = instance()
                )
            )
        }
    }
}