package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.darkos.kmp.androidApp.common.AndroidAlertProcessor
import com.darkos.kmp.androidApp.common.AndroidRouter
import com.darkos.kmp.androidApp.ui.home.HomeRoute
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.*
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI

@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by retainedSubDI(di()) {
    }

    override val diTrigger = DITrigger()

    private val alertProcessor: AndroidAlertProcessor by instance()
    private val router = AndroidRouter(HomeRoute(di))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        alertProcessor.attach(this)

        setContent {
            router.current.View()
        }
    }
}
