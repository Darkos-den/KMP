package com.darkos.kts.domain.effectHandler

import com.darkos.kts.domain.model.exception.NotSupportedMessageException
import com.darkos.kts.domain.model.mvu.sample.SampleEffect
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

internal class AppEffectHandler(
    private val sampleEffectHandler: SampleEffectHandler
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SampleEffect -> sampleEffectHandler.call(effect)
            else -> throw NotSupportedMessageException()
        }
    }
}