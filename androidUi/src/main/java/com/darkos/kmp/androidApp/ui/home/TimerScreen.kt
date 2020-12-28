package com.darkos.kmp.androidApp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.darkos.kmp.feature.timer.api.model.TimerState

private fun calculateButtonText(state: TimerState): String{
    return if(state.progress) "stop" else "start"
}

@Composable
fun TimerScreen(
    state: TimerState,
    onTextChanged: (String)->Unit,
    onStopClick: ()->Unit,
    onStartClick: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.progress){
            Text(
                text = state.value.toString()
            )
        }else {
            TextField(
                value = state.str,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    onTextChanged(it)
                }
            )
        }

        Button(onClick = {
            if(state.progress){
                onStopClick()
            }else{
                onStartClick()
            }
        }) {
            Text(text = calculateButtonText(state))
        }
    }
}