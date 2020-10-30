package com.darkos.core.presentation

import com.darkos.core.mvu.FlowComponent
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.MVUState
import com.darkos.mvu_program.Program
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class ComponentViewModel<T : MVUState, EH : EffectHandler, R : Reducer<T>>(
    effectHandler: EH,
    reducer: R
) : BaseViewModelImpl(), FlowComponent<T> {

    abstract override val initial: T
    override val state = MutableStateFlow(initial)

    protected val program: Program<T> by lazy {
        Program(
            initialState = initial,
            component = this,
            effectHandler = effectHandler,
            reducer = reducer
        )
    }

    init {
        ioScope.launch {
            delay(200)
            program.start()
        }
    }

    override fun onCleared() {
        program.clear()
        super.onCleared()
    }
}