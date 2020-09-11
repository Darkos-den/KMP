package com.company.projectName.android.ui.splash

import android.os.Bundle
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.LayoutFragment
import io.dynamax.android.R
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SplashFragment : LayoutFragment(
    layoutId = R.layout.fragment_splash
) {
    override val viewModelModule = Kodein.Module("HomeVM") {
        bindViewModel<SplashViewModel>() with provider {
            SplashViewModel(
                effectHandler = instance()
            )
        }
    }

    override val viewModel: SplashViewModel by viewModel()

    override fun viewCreated(savedInstanceState: Bundle?) {
        //do nothing
        viewModel
    }
}