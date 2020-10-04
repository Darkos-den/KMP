package com.company.projectName.login.model

import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.MVUState

class StatusProcessor<T : MVUState> private constructor(
    val onStateChanged: ((T, Boolean) -> T)?,
    val onSuccess: (() -> Effect)?,
    val onFailed: ((Exception) -> Effect)?
){
    class Builder<T : MVUState> {
        private var onStateChanged: ((T, Boolean) -> T)? = null
        private var onSuccess: (() -> Effect)? = null
        private var onFailed: ((Exception) -> Effect)? = null

        fun OnStateChanged(block: (T, Boolean) -> T) {
            onStateChanged = block
        }

        fun OnSuccess(block: () -> Effect) {
            onSuccess = block
        }

        fun OnFailed(block: (Exception) -> Effect) {
            onFailed = block
        }

        internal fun build(): StatusProcessor<T>? = StatusProcessor(
            onStateChanged,
            onSuccess,
            onFailed
        )
    }
}