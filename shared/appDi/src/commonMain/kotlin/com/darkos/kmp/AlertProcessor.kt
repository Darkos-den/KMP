package com.darkos.kmp

import com.darkos.kmp.feature.timer.api.alertProcessor.AlertProcessor

class AlertProcessor: AlertProcessor {

    private var alertShow: ((String)->Unit)? = null

    fun onAlertShowRequest(block: (String)->Unit){
        alertShow = block
    }

    fun showAlert(message: String){
        alertShow?.invoke(message)
    }

    override fun showMessage(message: String) {
        showAlert(message)
    }
}