package com.darkos.kmp.androidApp.ui.debug

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun DebugModeScreen(
    currentScreenName: String,
    logScreenChanges: Boolean,
    useTestAccount: Boolean,
    onLogScreenChanged: (Boolean)->Unit,
    onUseQuickAuthorizationChanged: (Boolean)->Unit
) {
    MainContainer {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CurrentScreen(currentScreenName)
            Spacer(modifier = Modifier.height(8.dp))
            LogScreensSwitch(logScreenChanges, onLogScreenChanged)
            Spacer(modifier = Modifier.height(8.dp))
            UseQuickAuthorization(useTestAccount, onUseQuickAuthorizationChanged)
            Spacer(modifier = Modifier.height(8.dp))
            Environment()
        }
    }
}

@Composable
private fun CurrentScreen(currentScreenName: String) {
    Text(
        text = "Current screen: $currentScreenName",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun LogScreensSwitch(
    state: Boolean,
    onCheckedChanged: (Boolean)->Unit
) {
    CheckBoxWithText(
        state = state,
        text = "Log screen changes",
        onCheckedChanged = onCheckedChanged
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
private fun UseQuickAuthorization(
    state: Boolean,
    onCheckedChanged: (Boolean)->Unit
) {
    Column {
        var current by mutableStateOf(state)
        CheckBoxWithText(
            state = current,
            text = "Use quick authorization",
            onCheckedChanged = {
                onCheckedChanged(it)
                current = it//todo
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
private fun Environment() {
    Column {
        Text(text = "Environment:")

        val options = listOf(
            "Local", "Dev", "Prod"
        )

        var selectedItem = "Dev"//todo: save to shared preferences

        RadioButton(selected = false, onClick = { /*TODO*/ })
        RadioButton(selected = false, onClick = { /*TODO*/ })
        RadioButton(selected = false, onClick = { /*TODO*/ })
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
    DebugModeScreen(
        logScreenChanges = false,
        useTestAccount = false,
        onLogScreenChanged = {},
        onUseQuickAuthorizationChanged = {},
        currentScreenName = "temp"
    )
}