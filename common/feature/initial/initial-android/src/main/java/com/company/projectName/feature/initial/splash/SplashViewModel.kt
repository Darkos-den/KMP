package com.company.projectName.feature.initial.splash

import com.company.projectName.initial.model.mvu.splash.SplashState
import com.company.projectName.initial.splash.ISplashEffectHandler
import com.company.projectName.initial.splash.ISplashReducer
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu_program.Program

class SplashViewModel(
    effectHandler: ISplashEffectHandler,
    reducer: ISplashReducer
) : BaseViewModelImpl(), Component<SplashState> {

    private val program = Program(
        initialState = SplashState(),
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

    override fun render(state: SplashState) {
        //do nothing
    }
}