package com.company.projectName.domain.feature.login

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.login.EmailPassordFeature
import com.company.projectName.login.EmailPasswordMessage
import com.company.projectName.login.LoginFeature
import com.company.projectName.login.model.EmailPassword
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

data class Vhod(
    val emailPassword: EmailPassword,
    val emailError: String,
    val passwordError: String,
    val progress: Boolean
) : MVUState()

val feature = LoginFeature<Vhod> {
    ProcessStatus {
        OnSuccess {
            NavigationEffect.NavigateToHome
        }
        OnFailed {
            GeneralEffect.ShowUserMessage("error")
        }
        OnStateChanged { state, value ->
            state.copy(progress = value)
        }
    }
    WithoutValidation {

    }
    WithValidation {

    }
}

val loginReducer = reducer<LoginScreenState> { state, message ->
    if (message is EmailPasswordMessage || message is LoginMessage) {
        return@reducer feature.update(state.vhod, message).map {
            state.copy(vhod = it)
        }
    }

    state.updateWithoutCmd()
}