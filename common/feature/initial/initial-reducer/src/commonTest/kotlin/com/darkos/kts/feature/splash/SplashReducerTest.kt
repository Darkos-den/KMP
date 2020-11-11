package com.darkos.kts.feature.splash

import com.darkos.kts.feature.splash.model.mvu.SplashEffect
import com.darkos.kts.feature.splash.model.mvu.SplashMessage
import com.darkos.kts.feature.splash.model.mvu.SplashState
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.None
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class SplashReducerTest {

    @Test
    fun checkInitComponentMessage() {
        val initialState = SplashState

        SplashReducer().update(initialState, ComponentInitialized).let {
            assertEquals(initialState, it.state)
            assertEquals(SplashEffect.CheckActiveUser, it.effect)
        }
    }

    @Test
    fun checkUserFoundMessage() {
        val initialState = SplashState

        SplashReducer().update(initialState, SplashMessage.UserFound).let {
            assertEquals(initialState, it.state)
            assertEquals(SplashEffect.Navigation.NavigateToMain, it.effect)
        }
    }

    @Test
    fun checkUserNotFoundMessage() {
        val initialState = SplashState

        SplashReducer().update(initialState, SplashMessage.UserNotFound).let {
            assertEquals(initialState, it.state)
            assertEquals(SplashEffect.Navigation.NavigateToLogin, it.effect)
        }
    }

    @Test
    fun checkOtherMessage() {
        val initialState = SplashState

        SplashReducer().update(initialState, Idle()).let {
            assertEquals(initialState, it.state)
            assertTrue {
                it.effect is None
            }
        }
    }
}