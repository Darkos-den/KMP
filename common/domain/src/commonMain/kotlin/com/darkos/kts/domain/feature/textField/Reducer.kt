package com.darkos.kts.domain.feature.textField

fun processTextFieldValueChanged(value: String): TextFieldState {
    return TextFieldState.Edit(
        value = value
    )
}

fun processError(oldState: TextFieldState, error: String?): TextFieldState {
    return if (error == null) {
        TextFieldState.Edit(value = oldState.value)
    } else {
        TextFieldState.Error(
            value = oldState.value,
            error = error
        )
    }
}