package com.darkos.kmp.feature.drawer

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay

class DrawerEffectHandler : IDrawerEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}