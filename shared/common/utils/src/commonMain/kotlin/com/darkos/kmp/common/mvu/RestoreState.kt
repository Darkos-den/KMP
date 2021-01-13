package com.darkos.kmp.common.mvu

import com.darkos.mvu.model.MVUState
import com.darkos.mvu.model.Message

data class RestoreState<T : MVUState>(
    val state: T
) : Message()//todo: move to core