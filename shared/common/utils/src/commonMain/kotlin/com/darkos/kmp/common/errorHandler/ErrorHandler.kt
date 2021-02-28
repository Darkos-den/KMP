package com.darkos.kmp.common.errorHandler

import com.darkos.mvu.Ui
import com.darkos.mvu.model.*
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

    suspend fun processErrorEffect(effect: ErrorEffect): Message {
        when (effect) {
            is ErrorEffect.Network -> {
                onNetworkError()
            }
            is ErrorEffect.App -> {
                onAppError(effect.message)
                Idle
            }
        }
        return Idle
    }
}

sealed class ErrorMessage : Message() {
    object Network : ErrorMessage()
    class App(val message: String) : ErrorMessage()
}

sealed class ErrorEffect : Effect() {
    object Network : ErrorEffect()
    class App(val message: String) : ErrorEffect()
}

fun <T : MVUState> processErrorMessage(message: ErrorMessage, block: () -> T): StateCmdData<T> {
    return when (message) {
        is ErrorMessage.Network -> {
            block() andEffect ErrorEffect.Network
        }
        is ErrorMessage.App -> {
            block() andEffect ErrorEffect.App(message.message)
        }
    }
}

suspend fun runAndHandleErrors(block: suspend () -> Message): Message {
    return try {
        block()
    } catch (e: Exception) {
        if (e.toString().contains("HostException")) {
            ErrorMessage.Network
        } else {
            ErrorMessage.App(e.message.orEmpty())
        }
    }
}