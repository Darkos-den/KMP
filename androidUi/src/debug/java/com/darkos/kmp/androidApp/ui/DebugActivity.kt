package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import com.darkos.kmp.androidApp.ui.debug.DebugModeScreen
import com.darkos.kmp.common.debugFeatures.DebugFeaturesManager
import com.darkos.kmp.common.debugFeatures.DebugScreenProcessor
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI
import org.kodein.di.instance

class DebugActivity : AppCompatActivity(), DIAware {

    override val di: DI by retainedSubDI(di()) {}

    override val diTrigger = DITrigger()

    private val debugFeaturesManager: DebugFeaturesManager by instance()
    private val debugScreenProcessor: DebugScreenProcessor by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        setContent {
            DebugModeScreen(
                logScreenChanges = debugFeaturesManager.logScreenChanges.collectAsState(false).value,
                useTestAccount = debugFeaturesManager.useTestAccount.collectAsState(false).value,
                onLogScreenChanged = debugFeaturesManager::setLogScreenChanges,
                onUseQuickAuthorizationChanged = debugFeaturesManager::setUseTestAccount,
                currentScreenName = debugScreenProcessor.currentScreenName.collectAsState("").value
            )
        }
    }
}