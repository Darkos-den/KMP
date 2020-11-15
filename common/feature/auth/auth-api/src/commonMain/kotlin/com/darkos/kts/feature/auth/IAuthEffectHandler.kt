package com.darkos.kts.feature.auth

import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.flow.FlowEffect
import com.darkos.mvu.models.flow.FlowMessage
import kotlinx.coroutines.flow.Flow

interface IAuthEffectHandler : EffectHandler {
    override suspend fun call(effect: FlowEffect): Flow<FlowMessage> {
        throw IllegalStateException("this handler does not support calling as Flow")
    }
}