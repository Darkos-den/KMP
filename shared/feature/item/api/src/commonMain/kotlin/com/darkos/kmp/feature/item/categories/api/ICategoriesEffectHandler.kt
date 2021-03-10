package com.darkos.kmp.feature.item.categories.api

import com.darkos.mvu.EffectHandler
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.flow.FlowEffect
import kotlinx.coroutines.flow.Flow

interface ICategoriesEffectHandler : EffectHandler {

    override suspend fun <T> callAsFlow(effect: T): Flow<Message> where T : Effect, T : FlowEffect {
        throw IllegalStateException("operation not supported")
    }
}