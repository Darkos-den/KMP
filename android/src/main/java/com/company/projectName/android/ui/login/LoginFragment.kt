package com.company.projectName.android.ui.login

import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.company.projectName.domain.feature.login.LoginState
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.LayoutFragment
import io.dynamax.android.R
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class LoginFragment : LayoutFragment(
    layoutId = R.layout.fragment_login
) {
    override val viewModelModule = Kodein.Module("HomeVM") {
        bindViewModel<LoginViewModel>() with provider {
            LoginViewModel(
                effectHandler = instance()
            )
        }
    }

    override val viewModel: LoginViewModel by viewModel()

    override fun viewCreated(savedInstanceState: Bundle?) {
        (view as ViewGroup).setContent(Recomposer.current()) {
            val state by viewModel.state.observeAsState(initial = LoginState.Initial)

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Login")
                        }
                    )
                }
            ) {
                LoginScreen(
                    state = state,
                    emailChanged = viewModel::emailChanged,
                    passwordChanged = viewModel::passwordChanged,
                    passwordVisibleClick = viewModel::passwordVisibleClick,
                    submitClick = viewModel::submitClick
                )
            }
        }
    }
}