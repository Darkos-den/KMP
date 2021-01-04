package com.darkos.kmp.common.alertProcessor

interface IAlertProcessor {
    suspend fun showSimpleMessage(message: String)
}