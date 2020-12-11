package com.darkos.kmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state: TimerState by viewModel.state.observeAsState(
                initial = viewModel.createInitialState()
            )

            onDispose {
                viewModel.clearStateListener()
            }

            Column {
                Text(
                    text = calculateValue(state)
                )
                TextField(value = state.str, onValueChange = {
                    viewModel.onTextChanged(it)
                })
                Button(onClick = {
                    if(state.progress){
                        viewModel.onStopClick()
                    }else{
                        viewModel.onStartClick()
                    }
                }) {
                    Text(text = calculateButtonText(state))
                }
            }
        }
    }

    private fun calculateValue(state: TimerState): String{
        return if(state.progress) {
            state.value
        } else {
            state.count
        }.toString()
    }

    private fun calculateButtonText(state: TimerState): String{
        return if(state.progress) "stop" else "start"
    }
}
