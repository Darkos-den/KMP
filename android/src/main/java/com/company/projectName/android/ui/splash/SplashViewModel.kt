package com.company.projectName.android.ui.splash

import com.company.projectName.domain.feature.splash.SplashState
import com.company.projectName.domain.feature.splash.splashReducer
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program

class SplashViewModel(
    effectHandler: EffectHandler
): BaseViewModelImpl(), Component<SplashState> {

    private val program = Program(
        initialState = SplashState(),
        effectHandler = effectHandler,
        reducer = splashReducer,
        component = this
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