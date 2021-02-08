package com.darkos.kmp.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkos.kmp.common.debugFeatures.DebugFeaturesManager
import com.darkos.kmp.common.debugFeatures.DebugScreenProcessor

class DebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DebugModeScreen()
        }
    }

    @Composable
    private fun DebugModeScreen() {
        MainContainer {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Current screen: ${DebugScreenProcessor.currentScreenName}",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    var current by mutableStateOf(DebugFeaturesManager.logScreenChanges)

                    Text(text = "Log screen changes")
                    Spacer(modifier = Modifier.width(4.dp))
                    Checkbox(
                        checked = current,
                        onCheckedChange = {
                            DebugFeaturesManager.logScreenChanges =
                                DebugFeaturesManager.logScreenChanges.not()
                            current = DebugFeaturesManager.logScreenChanges
                        })
                }
            }
        }
    }

    @Composable
    private fun MainContainer(block: @Composable () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Debug mode")
                    }
                )
            }
        ) {
            block()
        }
    }

    @Composable
    @Preview
    fun Preview() {
        DebugModeScreen()
    }

}