package com.company.projectName.domain.common

import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

fun <T : MVUState> updateStateWithoutCmd(block: () -> T): StateCmdData<T> {
    return StateCmdData(
        block(),
        None()
    )
}

fun <T : MVUState> T.updateWithoutCmd(block: (T) -> T = { this }): StateCmdData<T> {
    return StateCmdData(
        block(this),
        None()
    )
}