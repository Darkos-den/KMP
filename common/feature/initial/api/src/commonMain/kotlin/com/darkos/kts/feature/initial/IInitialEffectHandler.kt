package com.darkos.kts.feature.initial

import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.flow.FlowEffect
import com.darkos.mvu.models.flow.FlowMessage
import kotlinx.coroutines.flow.Flow

interface IInitialEffectHandler : EffectHandler {
    override suspend fun call(effect: FlowEffect): Flow<FlowMessage> {
        throw IllegalStateException("this handler does not support calling as Flow")
    }
}