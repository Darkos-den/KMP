package com.company.projectName.feature.initial

import com.company.projectName.initial.IInitialEffectHandler
import com.company.projectName.initial.IInitialReducer
import com.company.projectName.initial.model.mvu.InitialState
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