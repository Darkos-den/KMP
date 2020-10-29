package com.darkos.kts.feature.auth.signin

import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.kts.feature.signin.ISignInEffectHandler
import com.darkos.kts.feature.signin.ISignInReducer
import com.darkos.kts.feature.signin.model.mvu.SignInState
import com.darkos.mvu.Component
import com.darkos.mvu_program.Program

class SignInViewModel(
    effectHandler: ISignInEffectHandler,
    reducer: ISignInReducer
): BaseViewModelImpl(), Component<SignInState> {

    private val program = Program(
        initialState = SignInState(
            email = "",
            passwordError = "",
            password = "",
            emailError = "",
            progress = false
        ),
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

    override fun render(state: SignInState) {
        TODO("Not yet implemented")
    }
}