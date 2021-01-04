package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.ui.home.TimerScreen
import com.darkos.kmp.feature.timer.api.INavigator
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.mvu.component.ProgramComponent
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.*
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI

@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by retainedSubDI(di()) {
        bind() from provider {
            AndroidAlertProcessor(alertProcessor = instance())
        }
        bind<INavigator>() with provider {
            object: INavigator{
                override fun navigateToOtherTimer() {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    override val diTrigger = DITrigger()

    val viewModel: MainViewModel by viewModels()

    private val alertProcessor: AndroidAlertProcessor by instance()

    inline fun <reified T: ProgramComponent<*>> getOrInit(): T{
        return if(viewModel.isValidComponent<T>()){
            return viewModel.component as T
        }else{
            di.direct.instance<T>().also{
                viewModel.attach(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        alertProcessor.attach(this)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = home){
                composable(home){
                    getOrInit<ITimerComponent>().let {
                        var state by savedInstanceState { it.createInitialState() }
                        it.applyStateListener {
                            state = it
                        }

                        onDispose {
                            it.clearStateListener()
                        }

                        TimerScreen(
                            state = state,
                            onTextChanged = it::onTextChanged,
                            onStopClick = it::onStopClick,
                            onStartClick = it::onStartClick
                        )
                    }
                }
                composable(other){
                    Text(text = "other")
                }
            }
        }
    }

    companion object Despinations {
        val home = "home"
        val other = "other"
    }
}
