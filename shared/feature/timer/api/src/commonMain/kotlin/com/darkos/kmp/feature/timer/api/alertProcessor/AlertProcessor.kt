package com.darkos.kmp.feature.timer.api.alertProcessor

import com.darkos.mvu.Ui
import kotlinx.coroutines.withContext

class AlertProcessor: IAlertProcessor {
    var showSimpleMessage: ((String)->Unit)? = null

    override suspend fun showSimpleMessage(message: String) {
        withContext(Ui) {
            showSimpleMessage?.invoke(message)
        }
    }
}