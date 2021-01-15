package com.darkos.kmp.feature.splash

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.kmp.common.errorHandler.processErrorMessage
import com.darkos.kmp.common.mvu.RestoreState
import com.darkos.kmp.common.mvu.andEffect
import com.darkos.kmp.common.mvu.restoreStateAndEffect
import com.darkos.kmp.feature.splash.api.ISplashReducer
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.StateCmdData

class SplashReducer : ISplashReducer {

    override fun update(state: SplashState, message: Message): StateCmdData<SplashState> {
        return when (message) {
            is ComponentInitialized -> {
                if (state is SplashState.Init) {
                    SplashState.PrepareData andEffect SplashEffect.CheckAuthState
                } else state.none()
            }
            is RestoreState<*> -> {
                restoreStateAndEffect(message) {
                    if (it is SplashState.RefreshTokenError) {
                        SplashState.PrepareData andEffect SplashEffect.RetryTokenRefresh
                    } else {
                        it.none()
                    }
                }
            }
            is ErrorMessage -> {
                processErrorMessage(message) {
                    SplashState.RefreshTokenError
                }
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}