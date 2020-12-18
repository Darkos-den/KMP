package com.darkos.kmp.feature.timer.api.alertProcessor

class AlertProcessorImpl: AlertProcessor {

    private var alertShow: ((String)->Unit)? = null

    fun onAlertShowRequest(block: (String)->Unit){
        alertShow = block
    }

    override fun showMessage(message: String) {
        alertShow?.invoke(message)
    }
}