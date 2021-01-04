package com.darkos.kmp.feature.timer.api.model

import com.darkos.kmp.common.Parcelable
import com.darkos.kmp.common.Parcelize
import com.darkos.mvu.model.MVUState

@Parcelize
data class TimerState(
    val count: Int,
    val value: Int,
    val progress: Boolean,
    val str: String
): MVUState(), Parcelable {
    companion object{
        fun empty() = TimerState(
            count = 5,
            value = 0,
            progress = false,
            str = ""
        )
    }
}