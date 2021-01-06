package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.*
import com.darkos.kmp.feature.splash.model.SplashMessage
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
        return SplashState(0)
    }

    init {
        errorHandler.applyHandler(this)
    }

    override fun clear() {
        errorHandler.clearComponentListeners()
        super.clear()
    }

    override fun onPlusClicked() {
        accept(SplashMessage.Plus)
    }

    override fun onNextClicked() {
        accept(SplashMessage.Next)
    }
}

@OptIn(InternalCoroutinesApi::class)
fun ErrorHandler.applyHandler(handler: MVUComponent<*>) {
    this.doOnLogout {
        handler.accept(Logout)
    }
    this.doOrRetry {
        handler.accept(Retry)
    }
}

fun ErrorHandler.clearComponentListeners() {
    this.doOnLogout {}
    this.doOrRetry {}
}