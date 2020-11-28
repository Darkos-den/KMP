package com.darkos.kts.feature.initial

import com.darkos.kts.feature.initial.model.mvu.InitialEffect
import com.darkos.kts.feature.initial.model.mvu.InitialState
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.None
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InitialReducerTest {

    @Test
    fun checkInitComponentMessage() {
        val initialState = InitialState

        InitialReducer().update(initialState, ComponentInitialized).let {
            assertEquals(initialState, it.state)
            assertEquals(InitialEffect.Navigation.NavigateToSplash, it.effect)
        }
    }

    @Test
    fun checkOtherMessage() {
        val initialState = InitialState

        InitialReducer().update(initialState, Idle()).let {
            assertEquals(initialState, it.state)
            assertTrue {
                it.effect is None
            }
        }
    }
}