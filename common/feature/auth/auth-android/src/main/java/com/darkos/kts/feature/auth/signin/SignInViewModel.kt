package com.darkos.kts.feature.auth.signin

import com.darkos.core.presentation.ComponentViewModel
import com.darkos.kts.feature.signin.ISignInEffectHandler
import com.darkos.kts.feature.signin.ISignInReducer
import com.darkos.kts.feature.signin.model.mvu.SignInMessage
import com.darkos.kts.feature.signin.model.mvu.SignInState

class SignInViewModel(
    effectHandler: ISignInEffectHandler,
    reducer: ISignInReducer
) : ComponentViewModel<SignInState, ISignInEffectHandler, ISignInReducer>(
    effectHandler, reducer
) {

    override val initial = SignInState(
        email = "",
        passwordError = "",
        password = "",
        emailError = "",
        progress = false
    )

    fun onEmailChanged(text: String) {
        program.accept(SignInMessage.EmailChanged(text))
    }
}