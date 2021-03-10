package com.darkos.kmp.feature.drawer

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.kmp.feature.drawer.api.IDrawerNavigation
import com.darkos.kmp.feature.drawer.model.DrawerEffect
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay

class DrawerEffectHandler(
    private val authManager: AuthManager,
    private val navigation: IDrawerNavigation
) : IDrawerEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is DrawerEffect.Logout -> {
                authManager.signOut()
                navigate(navigation::fromDrawerToSignIn)
            }
            is DrawerEffect.OpenDashboard -> navigate(navigation::fromDrawerToDashboard)
            is DrawerEffect.OpenCategories -> navigate(navigation::fromDrawerToCategories)
            is DrawerEffect.OpenSearch -> navigate(navigation::fromDrawerToSearch)
            is DrawerEffect.OpenProfile -> navigate(navigation::fromDrawerToProfile)
            is DrawerEffect.OpenContacts -> navigate(navigation::fromDrawerToContacts)
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}