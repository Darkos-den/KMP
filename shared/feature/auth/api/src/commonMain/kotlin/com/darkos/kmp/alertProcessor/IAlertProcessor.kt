package com.darkos.kmp.alertProcessor

interface IAlertProcessor {
    suspend fun showSimpleMessage(message: String)
}