package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.ISplashComponent
import com.darkos.kmp.feature.splash.api.ISplashEffectHandler
import com.darkos.kmp.feature.splash.api.ISplashReducer
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)//todo: move annotation to core
class SplashComponent(
    reducer: ISplashReducer,
    effectHandler: ISplashEffectHandler
) : MVUComponent<SplashState>(
    reducer = reducer,
    effectHandler = effectHandler
), ISplashComponent {

    override fun createInitialState(): SplashState {
        return SplashState.Init
    }

    override fun restore(state: SplashState) {//todo: move to core
        accept(RestoreState(state))
    }
}