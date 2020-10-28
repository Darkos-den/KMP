package com.darkos.projectName.feature.initial.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.base.BaseFragment
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SplashFragment: BaseFragment() {

    override val viewModelModule = Kodein.Module("Splash.VM") {
        bindViewModel<SplashViewModel>() with provider {
            SplashViewModel(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }
    override val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    CircularProgressIndicator()
                }
            }
        }
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
    }
}