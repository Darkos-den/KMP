package com.darkos.kmp

class AlertProcessor {

    private var alertShow: ((String)->Unit)? = null

    fun onAlertShowRequest(block: (String)->Unit){
        alertShow = block
    }

    fun showAlert(message: String){
        alertShow?.invoke(message)
    }
}