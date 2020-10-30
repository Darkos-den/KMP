package com.darkos.kts.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.ui.tooling.preview.Preview
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.base.BaseFragment
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
                val state by viewModel.state.collectAsState(context = Dispatchers.Main.immediate)

                rootView(
                    state = state,
                    onEmailChanged = viewModel::onEmailChanged
                )
            }
        }
    }

    @Composable
    private fun rootView(
        state: SignInState,
        onEmailChanged: (String) -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "text")
                    }
                )
            }
        ) {
            TextField(
                value = state.email,
                onValueChange = onEmailChanged
            )
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
            onEmailChanged = {}
        )
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
    }
}