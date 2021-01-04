package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.ISplashReducer
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
                SplashState.Progress andEffect SplashEffect.CheckAuthState
            }
            is SplashMessage.ServerError -> {
                SplashState.ServerError.none()
            }
            is SplashMessage.ConnectionError -> {
                SplashState.NetworkError.none()
            }
            is SplashMessage.RetryClick -> {
                when (state) {
                    is SplashState.NetworkError,
                    SplashState.ServerError -> {
                        SplashState.Progress andEffect SplashEffect.RetryTokenRefresh
                    }
                    else -> throw IllegalStateException()
                }
            }
            is SplashMessage.LogoutClick -> {
                state andEffect SplashEffect.Logout
            }
            else -> throw IllegalArgumentException()
        }
    }
}