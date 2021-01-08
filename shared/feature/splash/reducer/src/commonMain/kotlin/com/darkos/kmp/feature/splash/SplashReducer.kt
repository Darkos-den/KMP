package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.ISplashReducer
import com.darkos.kmp.feature.splash.model.RestoreState
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashMessage
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class SplashReducer : ISplashReducer {

    private infix fun <T : MVUState> T.andEffect(cmd: Effect) =
        StateCmdData(state = this, effect = cmd)//todo: move to core lib

    override fun update(state: SplashState, message: Message): StateCmdData<SplashState> {
        return when (message) {
            is ComponentInitialized -> {
                if (state is SplashState.Init) {
                    state andEffect SplashEffect.CheckAuthState
                } else state.none()
            }
            is SplashMessage.NetworkError -> {
                SplashState.RefreshTokenError andEffect SplashEffect.ProcessNetworkError
            }
            is SplashMessage.AppError -> {
                SplashState.RefreshTokenError andEffect SplashEffect.ProcessAppError(message.message)
            }
            is RestoreState<*> -> {
                message.state.let {
                    if (it !is SplashState) {
                        throw RuntimeException("invalid state: $it")//todo: create exception class
                    }
                    if (it is SplashState.RefreshTokenError) {
                        state andEffect SplashEffect.RetryTokenRefresh
                    } else it.none()
                }
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}