package com.company.projectName.domain.common

expect interface MessageProcessor{
    fun showMessage(message: String)
}