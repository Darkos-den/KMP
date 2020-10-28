package com.darkos.kts.domain.common

expect interface MessageProcessor{
    fun showMessage(message: String)
}