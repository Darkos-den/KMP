package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.SaverScope
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
import com.darkos.kmp.androidApp.ui.splash.SplashUiState
import com.darkos.kmp.androidApp.ui.splash.mapFromSplashUi
import com.darkos.kmp.androidApp.ui.splash.mapToSplashUi
import com.darkos.kmp.feature.splash.api.BaseComponent
import com.darkos.kmp.feature.splash.api.ErrorHandler
import com.darkos.kmp.feature.splash.api.ISplashComponent
import com.darkos.kmp.feature.splash.api.ISplashNavigation
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState
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

    private inline fun <reified T : ProgramComponent<*>> getOrInit(): T {
        Log.d("SKA", "get or init")
        return if (viewModel.isValidComponent<T>()) {
            viewModel.component as T
        } else {
            di.direct.instance<T>().also {
                viewModel.attach(it)
                it.start()
            }
        }
    }

    class ComponentStateSaver<Savable : Any, S : MVUState, T : BaseComponent<S>>(
        private val component: T,
        private val mapTo: (S) -> Savable,
        private val mapFrom: (Savable) -> S
    ) : Saver<S, Savable> {

        override fun restore(value: Savable): S {
            return mapFrom(value).also {
                component.restore(it)
            }
        }

        override fun SaverScope.save(value: S): Savable {
            return mapTo(value)
        }
    }

    @Composable
    private inline fun <reified C : BaseComponent<S>, Ui : Any, S : MVUState> ComponentScreen(
        noinline mapTo: (S) -> Ui,
        noinline mapFrom: (Ui) -> S,
        noinline render: @Composable (component: C, ui: Ui) -> Unit
    ) {
        val component = remember { getOrInit<C>() }

        var state by savedInstanceState(
            saver = ComponentStateSaver(
                component = component,
                mapTo = mapTo,
                mapFrom = mapFrom
            )
        ) { component.createInitialState() }

        component.applyStateListener { newState ->
            state = newState
        }

        render(component, mapTo(state))
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
                    ComponentScreen<ISplashComponent, SplashUiState, SplashState>(
                        mapTo = ::mapToSplashUi,
                        mapFrom = ::mapFromSplashUi
                    ) { component, ui ->
                        SplashScreen(
                            state = ui,
                            onPlus = component::onPlusClicked,
                            onNext = component::onNextClicked
                        )
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
