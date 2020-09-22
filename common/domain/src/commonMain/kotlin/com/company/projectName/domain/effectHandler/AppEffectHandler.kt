package com.company.projectName.domain.effectHandler

import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.domain.model.mvu.auth.AuthEffect
import com.company.projectName.domain.model.mvu.sample.SampleEffect
import com.company.projectName.domain.model.exception.NotSupportedMessageException
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

internal class AppEffectHandler(
    private val generalEffectHandler: GeneralEffectHandler,
    private val navigationEffectHandler: NavigationEffectHandler,
    private val authEffectHandler: AuthEffectHandler,
    private val sampleEffectHandler: SampleEffectHandler
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is NavigationEffect -> navigationEffectHandler.call(effect)
            is GeneralEffect -> generalEffectHandler.call(effect)
            is AuthEffect -> authEffectHandler.call(effect)
            is SampleEffect -> sampleEffectHandler.call(effect)
            else -> throw NotSupportedMessageException()
        }
    }
}