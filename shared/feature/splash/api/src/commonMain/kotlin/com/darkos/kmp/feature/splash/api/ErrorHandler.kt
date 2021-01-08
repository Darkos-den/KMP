package com.darkos.kmp.feature.splash.api

import com.darkos.mvu.Ui
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.withContext

//todo: move to common
object Logout : Message()

object DoLogout : Effect()

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