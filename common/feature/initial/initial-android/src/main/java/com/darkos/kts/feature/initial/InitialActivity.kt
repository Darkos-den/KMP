package com.darkos.kts.feature.initial

import android.os.Bundle
import com.darkos.core.messageProcessor.MessageProcessor
import com.darkos.core.presentation.activity.RounterActivity
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.core.presentation.viewModel.BaseViewModel
import com.darkos.feature.initial.R
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class InitialActivity: RounterActivity() {

    override val viewModelModule = Kodein.Module("Initial.VM"){
        bindViewModel<InitialViewModel>() with provider {
            InitialViewModel(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }
    override val routerModule = Kodein.Module("Initial.Router"){
        bind<ActivityRouter>() with provider {
            instance<InitialRouter>()
        }
    }

    override val router: ActivityRouter by instance()
    private val messageProcessor: MessageProcessor by instance()
    private val viewModel: InitialViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
    }

    override fun onStart() {
        super.onStart()
        messageProcessor.attach(this)
        viewModel.trigger()//for initialization viewModel, todo: refactor
    }

    override fun onStop() {
        messageProcessor.detach()
        super.onStop()
    }

    private fun BaseViewModel.trigger(){
        this.hashCode()
    }
}