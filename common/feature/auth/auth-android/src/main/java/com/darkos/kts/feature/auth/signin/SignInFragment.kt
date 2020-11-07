package com.darkos.kts.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.base.BaseFragment
import com.darkos.kts.feature.auth.AuthRouter
import com.darkos.kts.feature.signin.UiData
import com.darkos.kts.feature.signin.model.mvu.SignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SignInFragment : BaseFragment() {

    override val viewModelModule = Kodein.Module("SignIn.VM") {
        bindViewModel<SignInViewModel>() with provider {
            SignInViewModel(
                effectHandler = instance(),
                reducer = instance(),
                router = instance(tag = AuthRouter.TAG)
            )
        }
    }
    override val viewModel: SignInViewModel by viewModel()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState(
                    context = Dispatchers.Main.immediate,
                    initial = viewModel.initial()
                )

                rootView(
                    state = state,
                    onEmailChanged = viewModel::onEmailChanged,
                    onPasswordChanged = viewModel::onPasswordChanged,
                    onSignInClick = viewModel::onSignInClick
                )
            }
        }
    }

    @OptIn(ExperimentalFocus::class)
    @Composable
    private fun rootView(
        state: SignInState,
        onEmailChanged: (String) -> Unit,
        onPasswordChanged: (String) -> Unit,
        onSignInClick: () -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = UiData.signInToolbarText)
                    }
                )
            }
        ) {
            val focusRequesters = List(2) { FocusRequester() }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(focusRequesters[0]),
                    value = state.email,
                    onValueChange = onEmailChanged,
                    keyboardType = KeyboardType.Email,
                    isErrorValue = state.emailError.isNotEmpty(),
                    label = {
                        Text(text = UiData.emailLabel)
                    },
                    errorColor = Color.Red,
                    imeAction = ImeAction.Next,
                    onImeActionPerformed = { _, _ ->
                        focusRequesters[1].requestFocus()
                    }
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(16.dp)
                )
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(focusRequesters[1]),
                    value = state.password,
                    onValueChange = onPasswordChanged,
                    isErrorValue = state.passwordError.isNotEmpty(),
                    label = {
                        Text(text = UiData.passwordLabel)
                    },
                    errorColor = Color.Red,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onImeActionPerformed = { _, controller ->
                        controller?.hideSoftwareKeyboard()
                    }
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(48.dp)
                )
                Button(
                    onClick = onSignInClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = UiData.signInButtonText)
                }
            }
        }
    }

    @Preview
    @Composable
    private fun previewState() {
        rootView(
            state = SignInState(
                email = "test@email.com",
                password = "password",
                emailError = "",
                passwordError = "",
                progress = false
            ),
            onEmailChanged = {},
            onPasswordChanged = {},
            onSignInClick = {}
        )
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
    }
}