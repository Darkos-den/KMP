package com.darkos.kts.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.base.BaseFragment
import com.darkos.core.presentation.viewModel.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SignInFragment: BaseFragment() {

    override val viewModelModule = Kodein.Module("Splash.VM") {
        bindViewModel<SignInViewModel>() with provider {
            SignInViewModel(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }
    override val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Text(text = "Hello world")
                }
            }
        }
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
    }
}