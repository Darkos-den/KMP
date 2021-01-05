package com.darkos.kmp.feature.splash.api

import com.darkos.mvu.model.Message

//todo: move to common
object Retry : Message()

class ErrorHandler {

    private var connectionErrorObserver: () -> Unit = {}
    private var appErrorObserver: (String) -> Unit = {}
    private var onRetryConnection: () -> Unit = {}

    fun clear() {
        connectionErrorObserver = {}
        onRetryConnection = {}
    }

    fun observeNetworkError(block: () -> Unit) {
        connectionErrorObserver = block
    }

    fun onNetworkError() {
        connectionErrorObserver()
    }

    fun onAppError(errorMessage: String) {
        appErrorObserver(errorMessage)
    }

    fun observeAppError(block: (String) -> Unit) {
        appErrorObserver = block
    }

    fun doOrRetry(block: () -> Unit) {
        onRetryConnection = block
    }

    fun retry() {
        onRetryConnection()
    }
}