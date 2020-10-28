package com.darkos.projectName.domain.effectHandler

import com.darkos.projectName.domain.model.exception.NotSupportedMessageException
import com.darkos.core.model.navigation.NavigationEffect
import com.darkos.projectName.domain.model.mvu.sample.SampleEffect
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

internal class AppEffectHandler(
    private val navigationEffectHandler: NavigationEffectHandler,
    private val sampleEffectHandler: SampleEffectHandler
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is NavigationEffect -> navigationEffectHandler.call(effect)
            is SampleEffect -> sampleEffectHandler.call(effect)
            else -> throw NotSupportedMessageException()
        }
    }
}