package com.darkos.kmp.androidApp.ui.home

import androidx.lifecycle.ViewModelProvider
import com.darkos.kmp.androidApp.bindViewModel
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.common.ComposeView
import com.darkos.kmp.androidApp.common.Route
import com.darkos.kmp.androidApp.viewModel
import com.darkos.kmp.androidApp.viewModel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.*

@InternalCoroutinesApi
class HomeRoute(parentDi: DI): Route(), DIAware {

    override val di: DI by subDI(parentDi) {
        bind<ViewModelProvider.Factory>() with provider { ViewModelFactory(direct) }//todo: check leak
        bindViewModel<TimerViewModel>() with provider {
            TimerViewModel(
                component = instance()
            )
        }
        bind() from provider {
            AndroidAlertProcessor(alertProcessor = instance())
        }
    }
    override val diTrigger = DITrigger()

    override val viewModel: TimerViewModel by viewModel()

    override val view: ComposeView = {
        it as TimerViewModel
            TimerScreen(
                state = it.state,
                onTextChanged = it::onTextChanged,
                onStopClick = it::onStopClick,
                onStartClick = it::onStartClick
            )
        }
}