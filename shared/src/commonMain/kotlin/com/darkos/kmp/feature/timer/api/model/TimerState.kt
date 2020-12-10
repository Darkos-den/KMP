package com.darkos.kmp.feature.timer.api.model

import com.darkos.mvu.model.MVUState

data class TimerState(
    val count: Int,
    val value: Int,
    val progress: Boolean
): MVUState()