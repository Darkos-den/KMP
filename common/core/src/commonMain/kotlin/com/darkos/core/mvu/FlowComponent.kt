package com.darkos.core.mvu

import com.darkos.mvu.Component
import com.darkos.mvu.models.MVUState
import kotlinx.coroutines.flow.MutableStateFlow

interface FlowComponent<T : MVUState> : Component<T> {
    fun initial(): T
    val state: MutableStateFlow<T>

    override fun render(state: T) {
        this.state.value = state
    }
}