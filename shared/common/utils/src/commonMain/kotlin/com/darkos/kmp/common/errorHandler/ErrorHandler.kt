package com.darkos.kmp.common.errorHandler

import com.darkos.mvu.Ui
import kotlinx.coroutines.withContext

class ErrorHandler {

    private var connectionErrorObserver: () -> Unit = {}
    private var appErrorObserver: (String) -> Unit = {}

    fun clear() {
        connectionErrorObserver = {}
        appErrorObserver = {}
    }

    fun observeNetworkError(block: () -> Unit) {
        connectionErrorObserver = block
    }

    suspend fun onNetworkError() = withContext(Ui) {
        connectionErrorObserver()
    }

    suspend fun onAppError(errorMessage: String) = withContext(Ui) {
        appErrorObserver(errorMessage)
    }

    fun observeAppError(block: (String) -> Unit) {
        appErrorObserver = block
    }
}