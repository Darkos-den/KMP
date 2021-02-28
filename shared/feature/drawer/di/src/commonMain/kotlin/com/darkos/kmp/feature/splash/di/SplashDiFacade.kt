package com.darkos.kmp.feature.splash.di

import com.darkos.kmp.feature.splash.api.ISplashComponent
import org.kodein.di.DirectDI
import org.kodein.di.instance

class SplashDiFacade(private val directDI: DirectDI) {
    fun getComponent(): ISplashComponent = directDI.instance()
}