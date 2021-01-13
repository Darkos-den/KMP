package com.darkos.kmp.androidApp.ui.auth.signIn

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.darkos.kmp.feature.signin.model.FieldState
import com.darkos.kmp.feature.signin.model.SignInState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FieldUiState(
    val text: String,
    val error: String?
) : Parcelable

@Parcelize
data class SignInUiState(
    val email: FieldUiState,
    val password: FieldUiState,
    val progress: Boolean
) : Parcelable

fun SignInState.map(): SignInUiState {
    return SignInUiState(
        email = FieldUiState(
            text = email.text,
            error = email.error
        ),
        password = FieldUiState(
            text = password.text,
            error = password.error
        ),
        progress = progress
    )
}

fun SignInUiState.map(): SignInState {
    return SignInState(
        email = FieldState(
            text = email.text,
            error = email.error
        ),
        password = FieldState(
            text = password.text,
            error = password.error
        ),
        progress = progress
    )
}

fun mapToSignInUi(state: SignInState): SignInUiState {
    return state.map()
}

fun mapFromSignInUi(state: SignInUiState): SignInState {
    return state.map()
}

@Composable
fun SignInScreen(
    state: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSubmitClick: () -> Unit,
    onSignUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "SignIn")
                }
            )
        }
    ) {
        Column {
            TextField(
                value = state.email.text,
                label = {
                    state.email.error?.let {
                        Text(text = it)
                    }
                },
                onValueChange = onEmailChanged
            )
            TextField(
                value = state.password.text,
                label = {
                    state.password.error?.let {
                        Text(text = it)
                    }
                },
                onValueChange = onPasswordChanged
            )
            Button(onClick = onSubmitClick) {
                Text(text = "Submit")
            }
            Button(onClick = onSignUp) {
                Text(text = "Sign Up")
            }
        }
    }
}