package com.company.projectName.android.ui.login

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.company.projectName.android.compose.FillParentProgressIndicator
import com.company.projectName.domain.feature.login.LoginState
import com.company.projectName.domain.feature.textField.TextFieldState

@Composable
fun LoginScreen(
    state: LoginState,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    passwordVisibleClick: () -> Unit,
    submitClick: () -> Unit
) {
    Stack {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            LoginTextField(
                state = state.email,
                textChanged = emailChanged,
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )

            PasswordField(
                state = state.password.fieldState,
                visible = state.password.visible,
                passwordVisibleClick = passwordVisibleClick,
                passwordChanged = passwordChanged
            )

            SubmitButton(submitClick)
        }

        if(state is LoginState.Progress){
            FillParentProgressIndicator()
        }
    }
}

@Composable
private fun PasswordField(
    state: TextFieldState,
    visible: Boolean,
    passwordChanged: (String) -> Unit,
    passwordVisibleClick: () -> Unit,
) {
    Stack {
        LoginTextField(
            state = state,
            textChanged = passwordChanged,
            keyboardType = if (visible) {
                KeyboardType.Text
            } else {
                KeyboardType.Password
            },
            visualTransformation = if (visible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)
            IconButton(
                onClick = passwordVisibleClick,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                if (visible) {
                    Icon(asset = Icons.Default.VisibilityOff)
                } else {
                    Icon(asset = Icons.Default.Visibility)
                }
            }
        }
    }
}

@Composable
private fun SubmitButton(
    submitClick: () -> Unit
) {
    Button(
        onClick = submitClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(50.dp)
    ) {
        Text(text = "Submit")
    }
}

@Composable
private fun LoginTextField(
    state: TextFieldState,
    textChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var selection by remember { mutableStateOf(TextRange(0, 0)) }

    TextField(
        value = TextFieldValue(
            text = state.value,
            selection = selection.let {
                if (it.start > state.value.length) {
                    TextRange(state.value.length, state.value.length)
                } else {
                    it
                }
            }
        ),
        label = {
            if (state is TextFieldState.Error) {
                Text(
                    text = state.error
                )
            } else {
                Text(
                    text = ""
                )
            }
        },
        isErrorValue = state is TextFieldState.Error,
        errorColor = Color.Red,
        inactiveColor = if (state is TextFieldState.Error) {
            Color.Red
        } else {
            MaterialTheme.colors.onSurface
        },
        onValueChange = {
            selection = it.selection

            if (it.text != state.value) {
                textChanged(it.text)
            }
        },
        keyboardType = keyboardType,
        visualTransformation = visualTransformation,
        modifier = modifier
    )
}

@Preview
@Composable
fun initialPreview() {
    LoginScreen(
        state = LoginState.Initial,
        emailChanged = {},
        passwordChanged = {},
        passwordVisibleClick = {},
        submitClick = {}
    )
}