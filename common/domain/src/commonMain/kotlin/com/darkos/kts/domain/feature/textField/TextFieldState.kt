package com.darkos.kts.domain.feature.textField

sealed class TextFieldState {
    abstract val value: String

    object Initial: TextFieldState(){
        override val value = ""
    }

    data class Edit(
        override val value: String
    ) : TextFieldState()

    data class Error(
        override val value: String,
        val error: String
    ) : TextFieldState()
}