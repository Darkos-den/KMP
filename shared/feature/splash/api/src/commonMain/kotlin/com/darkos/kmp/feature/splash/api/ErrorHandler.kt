package com.darkos.kmp.feature.splash.api

import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message

//todo: move to common
object Retry : Message()
object Logout : Message()

object DoLogout : Effect()

class ErrorHandler {

    private var connectionErrorObserver: () -> Unit = {}
    private var appErrorObserver: (String) -> Unit = {}
    private var onRetryConnection: () -> Unit = {}
    private var onLogout: () -> Unit = {}

    fun clear() {
        connectionErrorObserver = {}
        onRetryConnection = {}
        appErrorObserver = {}
        onLogout = {}
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

    fun doOnLogout(block: () -> Unit) {
        onLogout = block
    }

    fun retry() {
        onRetryConnection()
    }

    fun logout() {
        onLogout()
    }
}