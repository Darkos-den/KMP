package com.company.projectName.domain.feature.login

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.login.LoginFeature
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

private const val FIELD_ID_EMAIL: Long = 1
private const val FIELD_ID_PASSWORD: Long = 2

fun <T : MVUState, U : MVUState> StateCmdData<T>.map(mapper: (T) -> U): StateCmdData<U> {
    return StateCmdData(
        state = mapper(state),
        effect = effect
    )
}

val feature = LoginFeature<VhodState> {
    ValueChanes {
        registerMessage(LoginScreenMessage.EmailChanged::class) { state, message ->
            state.copy(email = message.newValue)
        }
        registerMessage(LoginScreenMessage.PasswordChanged::class) { state, message ->
            state.copy(password = message.newValue)
        }
    }

    LoginSuccessEffect {
        NavigationEffect.NavigateToHome
    }
    LoginFailedEffect { _, loginFailed ->
        GeneralEffect.ShowUserMessage(loginFailed.e.message.orEmpty())
    }
}

val loginReducer = reducer<LoginScreenState> { state, message ->
    if (message is LoginScreenMessage || message is LoginMessage) {
        return@reducer feature.update(state.vhod, message).map {
            state.copy(vhod = it)
        }
    }

    state.updateWithoutCmd()
}