package com.darkos.kts.feature.initial.splash

import com.darkos.kts.initial.model.mvu.splash.SplashState
import com.darkos.kts.initial.splash.ISplashEffectHandler
import com.darkos.kts.initial.splash.ISplashReducer
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