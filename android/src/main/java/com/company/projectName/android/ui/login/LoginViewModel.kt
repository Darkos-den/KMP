package com.company.projectName.android.ui.login

import com.company.projectName.domain.feature.login.LoginScreenMessage
import com.company.projectName.domain.feature.login.LoginScreenState
import com.company.projectName.domain.feature.login.feature
import com.company.projectName.domain.feature.login.loginReducer
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    effectHandler: EffectHandler
) : BaseViewModelImpl(), Component<LoginScreenState> {

//    private val stateSource = Channel<LoginScreenState>()
    val state: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState.Initial)

    var callback: (String)->Unit = {}

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
        this@LoginViewModel.state.value = state
    }

    fun emailChanged(value: String) {
        program.accept(LoginScreenMessage.EmailChanged(value))
    }

    fun passwordChanged(value: String) {
        program.accept(LoginScreenMessage.PasswordChanged(value))
    }

    fun passwordVisibleClick() {
//        program.accept(LoginScreenMessage.PasswordVisibleClick)
    }

    fun submitClick() {
        program.accept(LoginMessage.LoginClick)
    }
}