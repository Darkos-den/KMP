package com.darkos.kts.android.ui.main

import com.darkos.kts.domain.feature.main.MainState
import com.darkos.kts.domain.feature.main.mainReducer
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program

class MainViewModel(
    effectHandler: EffectHandler
) : BaseViewModelImpl(), Component<MainState> {

    private val program = Program(
        initialState = MainState(),
        component = this,
        effectHandler = effectHandler,
        reducer = mainReducer
    ).also {
        it.start()
    }

    override fun onCleared() {
        program.clear()
        super.onCleared()
    }

    override fun render(state: MainState) {
        //do nothing
    }
}