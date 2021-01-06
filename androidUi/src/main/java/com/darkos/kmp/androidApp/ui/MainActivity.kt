package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
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
import com.darkos.kmp.androidApp.ui.splash.map
import com.darkos.kmp.feature.splash.api.ErrorHandler
import com.darkos.kmp.feature.splash.api.ISplashComponent
import com.darkos.kmp.feature.splash.api.ISplashNavigation
import com.darkos.mvu.component.ProgramComponent
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.*
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI
import java.lang.ref.WeakReference

@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by retainedSubDI(di()) {
        bind() from provider {
            AndroidAlertProcessor(alertProcessor = instance())
        }
        bind() from singleton { Navigation() }
        bind<ISplashNavigation>() with provider { instance<Navigation>() }
    }

    override val diTrigger = DITrigger()

    private val viewModel: MainViewModel by viewModels()

    private val alertProcessor: AndroidAlertProcessor by instance()
    private val errorHandler: ErrorHandler by instance()
    private val navigation: Navigation by instance()

    class Navigation : ISplashNavigation {
        private var navController = WeakReference<NavController>(null)

        fun attach(navController: NavController) {
            this.navController = WeakReference(navController)
        }

        override fun goToLogin() {
            TODO("Not yet implemented")
        }

        override fun goToHome() {
            TODO("Not yet implemented")
        }
    }

    private inline fun <reified T : ProgramComponent<*>> getOrInit(block: (T) -> Unit) {
        Log.d("SKA", "get or init")
        if (viewModel.isValidComponent<T>()) {
            viewModel.component as T
        } else {
            di.direct.instance<T>().also {
//                viewModel.attach(it)
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
            navigation.attach(navController)

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
                    val component = remember { di.direct.instance<ISplashComponent>() }

                    if (viewModel.isValidComponent<ISplashComponent>().not()) {
                        viewModel.attach(component)
                    }

                    var state by savedInstanceState(
                        saver = Saver(
                            save = { it },
                            restore = {
                                it.also {
                                    component.restore(it.map())
                                }
                            }
                        )
                    ) { component.createInitialState().map() }

                    component.applyStateListener { newState ->
                        state = newState.map()
                    }

                    SplashScreen(
                        state = state,
                        onPlus = component::onPlusClicked,
                        onNext = component::onNextClicked
                    )
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
