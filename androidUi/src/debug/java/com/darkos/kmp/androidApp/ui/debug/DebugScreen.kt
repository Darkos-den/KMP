package com.darkos.kmp.androidApp.ui.debug

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkos.kmp.common.debugFeatures.DebugFeaturesManager
import com.darkos.kmp.common.debugFeatures.DebugScreenProcessor

@Composable
fun DebugModeScreen() {
    MainContainer {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CurrentScreen()
            Spacer(modifier = Modifier.height(8.dp))
            LogScreensSwitch()
            Spacer(modifier = Modifier.height(8.dp))
            UseTestAccount()
        }
    }
}

@Composable
private fun CurrentScreen() {
    Text(
        text = "Current screen: ${DebugScreenProcessor.currentScreenName}",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun LogScreensSwitch() {
    var current by mutableStateOf(DebugFeaturesManager.logScreenChanges)
    CheckBoxWithText(
        state = current,
        text = "Log screen changes",
        onCheckedChanged = {
            DebugFeaturesManager.logScreenChanges =
                DebugFeaturesManager.logScreenChanges.not()
            current = DebugFeaturesManager.logScreenChanges
        }
    )
}

@Composable
private fun CheckBoxWithText(
    state: Boolean,
    text: String,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.width(4.dp))
        Checkbox(
            checked = state,
            onCheckedChange = onCheckedChanged
        )
    }
}

@Composable
private fun UseTestAccount() {
    Column {
        var current by mutableStateOf(DebugFeaturesManager.useTestAccount)
        CheckBoxWithText(
            state = current,
            text = "Use test account",
            onCheckedChanged = {
                DebugFeaturesManager.useTestAccount =
                    DebugFeaturesManager.useTestAccount.not()
                current = DebugFeaturesManager.useTestAccount
            }
        )
        if (current) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Maybe there will be more users later",
                modifier = Modifier.padding(start = 16.dp)
            )
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

@Preview
@Composable
fun Preview() {
    DebugModeScreen()
}