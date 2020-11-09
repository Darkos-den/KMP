package com.darkos.kts.feature.initial

import com.darkos.kts.feature.initial.model.mvu.InitialEffect
import com.darkos.kts.feature.initial.model.mvu.InitialState
import com.darkos.mvu.models.ComponentInitialized
import kotlin.test.Test
import kotlin.test.assertEquals

class InitialReducerTest {

    @Test
    fun checkInitComponentMessage() {
        val initialState = InitialState()

        InitialReducer().update(InitialState(), ComponentInitialized).let {
            assertEquals(initialState, it.state)
            assertEquals(InitialEffect.Navigation.NavigateToSplash, it.effect)
        }
    }
}