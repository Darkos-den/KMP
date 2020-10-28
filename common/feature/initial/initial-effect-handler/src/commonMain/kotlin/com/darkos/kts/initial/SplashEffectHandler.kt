package com.darkos.kts.initial

import com.darkos.kts.initial.model.mvu.splash.SplashEffect
import com.darkos.kts.initial.model.mvu.splash.SplashMessage
import com.darkos.kts.initial.splash.ISplashEffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Idle
import com.darkos.mvu.models.Message
import kotlinx.coroutines.delay

class SplashEffectHandler: ISplashEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when(effect){
            is SplashEffect.Delay -> {
                delay(effect.millis)
                SplashMessage.DelayFinished
            }
            is SplashEffect.Navigation.NavigateToLogin -> {
                TODO()
            }
            is SplashEffect.Navigation.NavigateToMain -> {
                TODO()
            }
            else -> Idle()
        }
    }
}