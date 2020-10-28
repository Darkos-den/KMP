package com.darkos.projectName.android.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.darkos.projectName.domain.feature.home.HomeState
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.LayoutFragment
import com.darkos.kts.R
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class HomeFragment : LayoutFragment(
    layoutId = R.layout.fragment_home
) {
    override val viewModelModule = Kodein.Module("HomeVM") {
        bindViewModel<HomeViewModel>() with provider {
            HomeViewModel(
                effectHandler = instance()
            )
        }
    }

    override val viewModel: HomeViewModel by viewModel()

    override fun viewCreated(savedInstanceState: Bundle?) {
        (view as ViewGroup).setContent(Recomposer.current()) {
            val state by viewModel.state.observeAsState(initial = HomeState.Initial)

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Login")
                        }
                    )
                }
            ) {
                HomeScreen(
                    state = state,
                    reloadClick = viewModel::reloadContent
                )
            }
        }
    }
}