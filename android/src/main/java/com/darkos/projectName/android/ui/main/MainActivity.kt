package com.darkos.projectName.android.ui.main

import android.os.Bundle
import com.darkos.projectName.android.AppMessageProcessor
import com.darkos.projectName.domain.common.MessageProcessor
import com.darkos.core.presentation.activity.RounterActivity
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.kts.R
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MainActivity : RounterActivity() {

    override val viewModelModule = Kodein.Module("Main.VM") {
        bindViewModel<MainViewModel>() with provider {
            MainViewModel(
                effectHandler = instance()
            )
        }
    }

    override val routerModule = Kodein.Module("Main.Router") {
        bind<ActivityRouter>() with provider { instance<MainRouter>() }
    }

    private val messageProcessor: MessageProcessor by instance()

    private val viewModel: MainViewModel by viewModel()
    override val router: ActivityRouter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel
    }

    override fun onStart() {
        super.onStart()
        (messageProcessor as AppMessageProcessor).attach(this)
    }

    override fun onStop() {
        (messageProcessor as AppMessageProcessor).detach()
        super.onStop()
    }
}