package com.darkos.projectName.domain.common

expect interface MessageProcessor{
    fun showMessage(message: String)
}