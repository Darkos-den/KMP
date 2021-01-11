package com.darkos.kmp.feature.splash.di

import com.darkos.kmp.feature.splash.SplashComponent
import com.darkos.kmp.feature.splash.SplashEffectHandler
import com.darkos.kmp.feature.splash.SplashReducer
import com.darkos.kmp.feature.splash.api.ISplashComponent
import com.darkos.kmp.feature.splash.api.ISplashEffectHandler
import com.darkos.kmp.feature.splash.api.ISplashReducer
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class SplashDI {

    @OptIn(InternalCoroutinesApi::class)
    val module = DI.Module(TAG) {
        bind<ISplashReducer>() with provider { SplashReducer() }
        bind<ISplashEffectHandler>() with provider {
            SplashEffectHandler(
                remote = instance(),
                secure = instance(),
                navigation = instance(),
                errorHandler = instance()
            )
        }
        bind<ISplashComponent>() with provider {
            SplashComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Splash"
    }
}