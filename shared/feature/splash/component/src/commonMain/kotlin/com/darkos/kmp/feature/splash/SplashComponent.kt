package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.*
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.MVUComponent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
class SplashComponent(
    reducer: ISplashReducer,
    effectHandler: ISplashEffectHandler,
    private val connectionHandler: ErrorHandler
) : MVUComponent<SplashState>(
    reducer = reducer,
    effectHandler = effectHandler
), ISplashComponent {

    override fun createInitialState(): SplashState {
        return SplashState.Progress
    }

    init {
        connectionHandler.doOrRetry {
            accept(Retry)
        }
    }

    fun onLogoutClick() {
//        accept(SplashMessage.LogoutClick)
    }
}