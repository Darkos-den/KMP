package com.darkos.kmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import org.kodein.di.*
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI
import java.nio.file.WatchEvent

@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by retainedSubDI(di()) {
        bind() from provider {
            MainViewModel(
                component = instance()
            )
        }
    }

    override val diTrigger = DITrigger()

    private val viewModel: MainViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diTrigger.trigger()

        setContent {
            val state: TimerState by viewModel.state.observeAsState(
                initial = viewModel.createInitialState()
            )

            onDispose {
                viewModel.clearStateListener()
            }

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
                            viewModel.onTextChanged(it)
                        }
                    )
                }

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

    private fun calculateButtonText(state: TimerState): String{
        return if(state.progress) "stop" else "start"
    }
}
