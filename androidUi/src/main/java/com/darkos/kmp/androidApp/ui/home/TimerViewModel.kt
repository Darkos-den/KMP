package com.darkos.kmp.androidApp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkos.kmp.feature.timer.api.ITimerComponent
import com.darkos.kmp.feature.timer.api.model.TimerState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TimerViewModel(
    private val component: ITimerComponent
) : ViewModel(), ITimerComponent by component {

    var state by mutableStateOf(createInitialState())
        private set

    init {
        applyStateListener {
            state = it
        }
        start()
    }

    override fun onCleared() {
        clearStateListener()
        clear()
        super.onCleared()
    }
}