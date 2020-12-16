package com.darkos.kmp.feature.timer

import com.darkos.kmp.feature.timer.api.ITimerComponent
import org.kodein.di.DirectDI
import org.kodein.di.instance

class TimerDiFacade(private val directDI: DirectDI) {
    fun getComponent(): ITimerComponent = directDI.instance()
}