package com.company.projectName.feature.auth

import com.company.projectName.auth.IAuthEffectHandler
import com.company.projectName.auth.IAuthReducer
import com.company.projectName.auth.model.mvu.AuthState
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu_program.Program

class AuthViewModel(
    effectHandler: IAuthEffectHandler,
    reducer: IAuthReducer
) : BaseViewModelImpl(), Component<AuthState> {

    private val program = Program(
        initialState = AuthState(),
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

    override fun render(state: AuthState) {
        //do nothing
    }
}