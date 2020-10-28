package com.darkos.kts.feature.auth

import android.os.Bundle
import com.darkos.kts.domain.common.MessageProcessor
import com.darkos.core.presentation.activity.RounterActivity
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.core.presentation.viewModel.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AuthActivity: RounterActivity() {

    override val viewModelModule = Kodein.Module("Auth.VM"){
        bindViewModel<AuthViewModel>() with provider {
            AuthViewModel(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }
    override val routerModule = Kodein.Module("Auth.Router"){
        bind<ActivityRouter>() with singleton {
            AuthRouter(
                appNavigator = instance()
            )
        }
    }

    override val router: ActivityRouter by instance()
    private val messageProcessor: MessageProcessor by instance()
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.trigger()//for initialization viewModel, todo: refactor
    }

    override fun onStart() {
        super.onStart()
        messageProcessor.attach(this)
    }

    override fun onStop() {
        messageProcessor.detach()
        super.onStop()
    }

    private fun BaseViewModel.trigger(){
        this.hashCode()
    }
}