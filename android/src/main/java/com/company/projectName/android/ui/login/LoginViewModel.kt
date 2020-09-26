package com.company.projectName.android.ui.login

import com.company.projectName.domain.feature.login.LoginScreenState
import com.company.projectName.domain.feature.login.loginReducer
import com.company.projectName.login.EmailPasswordMessage
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    effectHandler: EffectHandler
) : BaseViewModelImpl(), Component<LoginScreenState> {

    private val stateSource = Channel<LoginScreenState>()
    val state: Flow<LoginScreenState> = channelFlow {
        while (true) {
            stateSource.receive().let {
                send(it)
            }
        }
    }

    private val program = Program(
        initialState = LoginScreenState.Initial,
        effectHandler = effectHandler,
        reducer = loginReducer,
        component = this
    ).also {
        it.start()
    }

    override fun onCleared() {
        program.clear()
        super.onCleared()
    }

    override fun render(state: LoginScreenState) {
        ioScope.launch {
            stateSource.send(state)
        }
    }

    fun emailChanged(value: String) {
        program.accept(EmailPasswordMessage.EmailChanged(value))
    }

    fun passwordChanged(value: String) {
        program.accept(EmailPasswordMessage.PasswordChanged(value))
    }

    fun passwordVisibleClick() {
//        program.accept(LoginScreenMessage.PasswordVisibleClick)
    }

    fun submitClick() {
        program.accept(LoginMessage.LoginClick)
    }
}