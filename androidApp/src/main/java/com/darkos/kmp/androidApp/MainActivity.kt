package com.darkos.kmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.darkos.kmp.shared.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.darkos.kmp.androidApp.databinding.ActivityMainBinding
import com.darkos.kmp.feature.timer.TimerComponent
import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val component: ITimerComponent by lazy {
        TimerDI().getComponent()
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        component.state
            .onEach(this::drawState)
            .launchIn(lifecycleScope)
    }

    private fun drawState(state: TimerState){
        if(state.progress){
            binding.button.setOnClickListener {
                component.onStopClick()
            }
        }else{
            binding.button.setOnClickListener {
                component.onStopClick()
            }
        }

        binding.text.setText(state.value)
    }
}
