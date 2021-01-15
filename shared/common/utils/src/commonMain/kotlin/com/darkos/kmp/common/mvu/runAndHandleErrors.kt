package com.darkos.kmp.common.mvu

import com.darkos.mvu.common.none
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.MVUState
import com.darkos.mvu.model.StateCmdData

infix fun <T : MVUState> T.andEffect(cmd: Effect) =
    StateCmdData(state = this, effect = cmd)//todo: move to core lib

inline fun <reified T : MVUState> restoreState(message: RestoreState<*>): StateCmdData<T> {
    return if (T::class.isInstance(message.state)) {
        (message.state as T).none()
    } else {
        throw UnsupportedOperationException()
    }
}