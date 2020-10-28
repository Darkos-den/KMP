package com.darkos.kts.feature.initial

import com.darkos.kts.feature.initial.IInitialEffectHandler
import com.darkos.kts.feature.initial.IInitialReducer
import com.darkos.kts.feature.initial.model.mvu.InitialState
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu_program.Program

class InitialViewModel(
    effectHandler: IInitialEffectHandler,
    reducer: IInitialReducer
) : BaseViewModelImpl(), Component<InitialState> {

    private val program = Program(
        initialState = InitialState(),
        component = this,
        effectHandler = effectHandler,
        reducer = reducer
    ).also {
        it.start()
    }

    override fun onCleared() {
        program.clear()
        super.onCleared()
    }

    override fun render(state: InitialState) {
        //do nothing
    }
}