package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay

class DashboardEffectHandler : IDashboardEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}