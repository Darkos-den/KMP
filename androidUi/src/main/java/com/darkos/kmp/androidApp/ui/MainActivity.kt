package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
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

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = home) {
                composable(home) {
                    Text(text = "home")
                }
                composable(other) {
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
