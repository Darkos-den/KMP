package com.company.projectName.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.projectName.domain.feature.login.LoginScreenMessage
import com.company.projectName.domain.feature.login.LoginState
import com.company.projectName.domain.feature.login.loginReducer
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program

class LoginViewModel(
    effectHandler: EffectHandler
) : BaseViewModelImpl(), Component<LoginState> {

    private val stateSource = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = stateSource

    private val program = Program(
        initialState = LoginState.Initial,
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

    override fun render(state: LoginState) {
        stateSource.postValue(state)
    }

    fun emailChanged(value: String){
        program.accept(LoginScreenMessage.EmailChanged(value))
    }

    fun passwordChanged(value: String){
        program.accept(LoginScreenMessage.PasswordChanged(value))
    }

    fun passwordVisibleClick(){
        program.accept(LoginScreenMessage.PasswordVisibleClick)
    }

    fun submitClick(){
        program.accept(LoginScreenMessage.SubmitClick)
    }
}