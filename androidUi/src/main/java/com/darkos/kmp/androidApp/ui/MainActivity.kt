package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.ui.error.app.AppErrorScreen
import com.darkos.kmp.androidApp.ui.error.connection.ConnectionErrorScreen
import com.darkos.kmp.androidApp.ui.splash.SplashScreen
import com.darkos.kmp.feature.splash.api.ErrorHandler
import com.darkos.kmp.feature.splash.api.ISplashComponent
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

    private val viewModel: MainViewModel by viewModels()

    private val alertProcessor: AndroidAlertProcessor by instance()
    private val errorHandler: ErrorHandler by instance()

    private inline fun <reified T : ProgramComponent<*>> getOrInit(block: (T) -> Unit) {
        Log.d("SKA", "get or init")
        if (viewModel.isValidComponent<T>()) {
            viewModel.component as T
        } else {
            di.direct.instance<T>().also {
                viewModel.attach(it)
                it.start()
            }
        }.let {
            block(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        alertProcessor.attach(this)

        viewModel.doWhenDestroy {
            errorHandler.clear()
        }

        setContent {
            val navController = rememberNavController()

            errorHandler.run {
                observeNetworkError {
                    (navController as NavController).navigate(networkError)
                }

                observeAppError {
                    (navController as NavController).navigate("$appError/$it")
                }
            }

            NavHost(navController = navController, startDestination = splash) {
                composable(splash) {
                    getOrInit<ISplashComponent> {
                        SplashScreen()
                    }
                }
                composable(networkError) {
                    ConnectionErrorScreen {
                        navController.popBackStack()
                        errorHandler.retry()
                    }
                }
                composable("$appError/{message}") {
                    AppErrorScreen(
                        message = it.arguments?.getString("message")!!,
                        onRetry = {
                            navController.popBackStack()
                            errorHandler.retry()
                        },
                        onLogout = {
                            errorHandler.logout()
                        }
                    )
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
