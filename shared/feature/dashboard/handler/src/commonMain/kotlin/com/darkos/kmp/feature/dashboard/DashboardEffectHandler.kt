package com.darkos.kmp.feature.dashboard

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.dashboard.api.IDashboardEffectHandler
import com.darkos.kmp.feature.dashboard.api.IDashboardNavigation
import com.darkos.kmp.feature.dashboard.model.DashboardEffect
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay

class DashboardEffectHandler(
    private val authManager: AuthManager,
    private val navigation: IDashboardNavigation
) : IDashboardEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is DashboardEffect.Logout -> {
                authManager.signOut()
                navigate(navigation::fromDashboardToSignIn)
            }
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}