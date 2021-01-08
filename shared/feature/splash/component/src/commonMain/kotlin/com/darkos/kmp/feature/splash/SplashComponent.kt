package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.*
import com.darkos.kmp.feature.splash.model.RestoreState
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.MVUComponent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
class SplashComponent(
    reducer: ISplashReducer,
    effectHandler: ISplashEffectHandler,
    private val errorHandler: ErrorHandler
) : MVUComponent<SplashState>(
    reducer = reducer,
    effectHandler = effectHandler
), ISplashComponent {

    override fun createInitialState(): SplashState {
        return SplashState.PrepareData
    }

    init {
        errorHandler.applyHandler(this)//todo: move to common
    }

    override fun clear() {
        errorHandler.clearComponentListeners()//todo: move to common
        super.clear()
    }

    override fun restore(state: SplashState) {//todo: move to core
        accept(RestoreState(state))
    }
}

@OptIn(InternalCoroutinesApi::class)
fun ErrorHandler.applyHandler(handler: MVUComponent<*>) {
    this.doOnLogout {
        handler.accept(Logout)
    }
}

fun ErrorHandler.clearComponentListeners() {
    this.doOnLogout {}
}