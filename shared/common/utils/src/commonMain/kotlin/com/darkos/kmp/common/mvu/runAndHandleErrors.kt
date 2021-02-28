package com.darkos.kmp.common.mvu

import com.darkos.mvu.Ui
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*
import com.darkos.mvu.model.RestoreState
import kotlinx.coroutines.withContext

inline fun <reified T : MVUState> restoreState(message: RestoreState<*>): StateCmdData<T> {
    return if (T::class.isInstance(message.state)) {
        (message.state as T).none()
    } else {
        throw UnsupportedOperationException()
    }
}

inline fun <reified T : MVUState> restoreStateAndEffect(
    message: RestoreState<*>,
    block: (T) -> StateCmdData<T>
): StateCmdData<T> {
    return if (T::class.isInstance(message.state)) {
        block(message.state as T)
    } else {
        throw UnsupportedOperationException()
    }
}

suspend fun navigate(block: () -> Unit): Message {
    withContext(Ui) {
        block()
    }
    return Idle
}