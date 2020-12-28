package com.darkos.kmp.androidApp.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState

abstract class PlatformComponent {
    private val subComponentManager = ComponentManager()
    abstract val component: ProgramComponent<MVUState>

    @Composable
    fun UI(){
        val state = savedInstanceState { component.createInitialState() }

        component.applyStateListener {
            state.value = it
        }

        //todo: render state
    }

    //todo: lifecycle
}