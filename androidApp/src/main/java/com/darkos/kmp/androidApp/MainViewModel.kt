package com.darkos.kmp.androidApp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkos.kmp.feature.timer.TimerDI
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainViewModel(
    private val component: ITimerComponent = TimerDI().getComponent()
): ViewModel(), ITimerComponent by component {

    val state = MutableLiveData<TimerState>()

    init {
        component.applyStateListener {
            state.value = it
        }
        component.start()
    }
}