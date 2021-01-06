package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.DoLogout
import com.darkos.kmp.feature.splash.api.ISplashReducer
import com.darkos.kmp.feature.splash.api.Logout
import com.darkos.kmp.feature.splash.api.Retry
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class SplashReducer : ISplashReducer {

    private infix fun <T : MVUState> T.andEffect(cmd: Effect) =
        StateCmdData(state = this, effect = cmd)//todo: move to core lib

    override fun update(state: SplashState, message: Message): StateCmdData<SplashState> {
        return when (message) {
            is ComponentInitialized -> {
                SplashState andEffect SplashEffect.CheckAuthState
            }
            is Retry -> {
                state andEffect SplashEffect.RetryTokenRefresh
            }
            is Logout -> {
                state andEffect DoLogout
            }
            is Idle -> state.none()
            else -> throw IllegalArgumentException("message: $message")
        }
    }
}