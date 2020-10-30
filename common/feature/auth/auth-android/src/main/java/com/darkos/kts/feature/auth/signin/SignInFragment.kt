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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.base.BaseFragment
import com.darkos.kts.feature.signin.UiData
import com.darkos.kts.feature.signin.model.mvu.SignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SignInFragment : BaseFragment() {

    override val viewModelModule = Kodein.Module("Splash.VM") {
        bindViewModel<SignInViewModel>() with provider {
            SignInViewModel(
                effectHandler = instance(),
                reducer = instance()
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
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = onEmailChanged
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = onPasswordChanged,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(48.dp))
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