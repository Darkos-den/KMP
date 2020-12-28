package com.darkos.kmp.androidApp.ui.home

import com.darkos.kmp.androidApp.common.PlatformComponent
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState

class HomePlatformComponent: PlatformComponent() {

    override val component = ITimerComponent
}