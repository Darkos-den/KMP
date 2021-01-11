package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.common.ComponentStateSaver
import com.darkos.kmp.androidApp.ui.auth.signIn.SignInScreen
import com.darkos.kmp.androidApp.ui.auth.signUp.SignUpScreen
import com.darkos.kmp.androidApp.ui.error.app.AppErrorScreen
import com.darkos.kmp.androidApp.ui.error.connection.ConnectionErrorScreen
import com.darkos.kmp.androidApp.ui.home.HomeScreen
import com.darkos.kmp.androidApp.ui.splash.SplashScreen
import com.darkos.kmp.androidApp.ui.splash.mapFromSplashUi
import com.darkos.kmp.androidApp.ui.splash.mapToSplashUi
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.feature.splash.api.BaseComponent
import com.darkos.kmp.feature.splash.api.ISplashNavigation
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
            navController.get()?.navigate(signIn)
        }

        override fun goToHome() {
            navController.get()?.navigate(home)
        }

        fun handleBack(activity: AppCompatActivity): Boolean {
            return navController.get()?.currentDestination?.label?.let {
                if (it == networkError || it == appError) {
                    activity.moveTaskToBack(true)
                    true
                } else {
                    false
                }
            } ?: false
        }
    }

    private inline fun <reified T : ProgramComponent<*>> getOrInit(): T {
        return if (viewModel.isValidComponent<T>()) {
            viewModel.component as T
        } else {
            di.direct.instance<T>().also {
                viewModel.attach(it)
                it.start()
            }
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

    @Composable
    private inline fun <reified C : BaseComponent<*>> ImmutableStateComponentScreen(
        noinline render: @Composable (component: C) -> Unit
    ) {
        val component = remember { getOrInit<C>() }
        render(component)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        alertProcessor.attach(this)

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
                    it.destination.label = splash

                    ComponentScreen(
                        mapTo = ::mapToSplashUi,
                        mapFrom = ::mapFromSplashUi
                    ) { _, _ ->
                        SplashScreen()
                    }
                }
                composable(networkError) {
                    it.destination.label = networkError

                    ConnectionErrorScreen {
                        navController.popBackStack()
                    }
                }
                composable("$appError/{message}") {
                    it.destination.label = appError

                    AppErrorScreen(
                        message = it.arguments?.getString("message")!!,
                        onRetry = {
                            navController.popBackStack()
                        },
                        onRestart = {
                            viewModel.clear()
                            (navController as NavController).navigate(splash) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
                composable(signIn) {
                    it.destination.label = signIn

                    SignInScreen()
                }
                composable(signUp) {
                    it.destination.label = signUp

                    SignUpScreen()
                }
                composable(home) {
                    it.destination.label = home

                    HomeScreen()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navigation.handleBack(this).not()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        errorHandler.clear()
        super.onDestroy()
    }

    companion object Destinations {
        private const val splash = "splash"
        private const val networkError = "error.network"
        private const val appError = "error.app"
        private const val signIn = "auth.singIn"
        private const val signUp = "auth.signUp"
        private const val home = "home"
    }
}