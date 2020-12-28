package com.darkos.kmp.feature.timer.api.alertProcessor

interface IAlertProcessor {
    suspend fun showSimpleMessage(message: String)
}