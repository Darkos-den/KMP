package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.ui.error.app.AppErrorScreen
import com.darkos.kmp.androidApp.ui.error.connection.ConnectionErrorScreen
import com.darkos.kmp.androidApp.ui.splash.SplashScreen
import com.darkos.kmp.feature.splash.api.ErrorHandler
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
    }

    override val diTrigger = DITrigger()

    val viewModel: MainViewModel by viewModels()

    private val alertProcessor: AndroidAlertProcessor by instance()
    private val connectionHandler: ErrorHandler by instance()

    inline fun <reified T : ProgramComponent<*>> getOrInit(): T {
        return if (viewModel.isValidComponent<T>()) {
            return viewModel.component as T
        } else {
            di.direct.instance<T>().also {
                viewModel.attach(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        alertProcessor.attach(this)

        viewModel.doWhenDestroy {
            connectionHandler.clear()
        }

        setContent {
            val navController = rememberNavController()

            connectionHandler.observeNetworkError {
                navController.popBackStack()
                connectionHandler.retry()
            }

            NavHost(navController = navController, startDestination = splash) {
                composable(splash) {
                    SplashScreen(
                        state =,
                        retryClick = { /*TODO*/ },
                        logoutClick = { /*TODO*/ }
                    )
                }
                composable(networkError) {
                    ConnectionErrorScreen()
                }
                composable(appError) {
                    AppErrorScreen()
                }
            }
        }
    }

    companion object Destinations {
        private const val splash = "splash"
        private const val networkError = "error.network"
        private const val appError = "error.app"
    }
}
