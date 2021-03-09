package com.darkos.kmp.feature.signin

import com.darkos.kmp.feature.signin.api.ISignInComponent
import com.darkos.kmp.feature.signin.api.ISignInEffectHandler
import com.darkos.kmp.feature.signin.api.ISignInReducer
import com.darkos.kmp.feature.signin.model.FieldState
import com.darkos.kmp.feature.signin.model.ScreenState
import com.darkos.kmp.feature.signin.model.SignInMessage
import com.darkos.kmp.feature.signin.model.SignInState
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState
import kotlinx.coroutines.InternalCoroutinesApi

class SignInComponent(
    reducer: ISignInReducer,
    effectHandler: ISignInEffectHandler
) : MVUComponent<SignInState>(
    effectHandler = effectHandler,
    reducer = reducer
), ISignInComponent {

    override fun onSubmitClick() {
        accept(SignInMessage.SubmitClicked)
    }

    override fun onEmailChanged(value: String) {
        accept(SignInMessage.EmailChanged(value))
    }

    override fun onPasswordChanged(value: String) {
        accept(SignInMessage.PasswordChanged(value))
    }

    override fun createInitialState(): SignInState {
        return SignInState(
            email = FieldState(
                text = "",
                error = null
            ),
            password = FieldState(
                text = "",
                error = null
            ),
            screenState = ScreenState.IDLE
        )
    }
}