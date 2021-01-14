package com.darkos.kmp.androidApp.ui.auth.signIn

import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
fun HorizontalSpace(size: Dp) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(size)
    )
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
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalSpace(size = 24.dp)

            TextField(
                value = state.email.text,
                label = {
                    state.email.error?.let {
                        Text(text = it)
                    }
                },
                onValueChange = onEmailChanged,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            HorizontalSpace(size = 8.dp)

            TextField(
                value = state.password.text,
                label = {
                    state.password.error?.let {
                        Text(text = it)
                    }
                },
                onValueChange = onPasswordChanged,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            HorizontalSpace(size = 16.dp)

            Button(
                onClick = onSubmitClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Submit")
            }

            HorizontalSpace(size = 8.dp)

            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview
@Composable
fun EmptyStatePreview() {
    SignInScreen(
        state = SignInUiState(
            email = FieldUiState(
                text = "",
                error = null
            ),
            password = FieldUiState(
                text = "",
                error = null
            ),
            progress = false
        ),
        onEmailChanged = {},
        onPasswordChanged = {},
        onSubmitClick = {},
        onSignUp = {}
    )
}