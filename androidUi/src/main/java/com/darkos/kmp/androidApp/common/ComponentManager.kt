package com.darkos.kmp.androidApp.common

import com.darkos.kmp.feature.timer.api.model.TimerState
import com.darkos.mvu.Component
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState
import kotlinx.coroutines.flow.MutableStateFlow
import com.darkos.kmp.feature.timer.api.INavigator as TimerNavigator

object EmptyState: MVUState()

class ComponentManager {
    val currentState = MutableStateFlow<MVUState>(EmptyState)

    private var backStack: List<ProgramComponent<*>> = emptyList()
        set(value) {
            field = value

            if(value.isNotEmpty()){
                value.last().applyStateListener {
                    currentState.value = it
                }
            }else{
                //todo: ?
            }
        }

    private fun pop(){
        backStack = backStack.removeLast()
    }

    private fun List<ProgramComponent<*>>.removeLast(): List<ProgramComponent<*>>{
        return if(this.isNotEmpty()){
            this - this.last().also {
                it.clearStateListener()
                it.clear()//todo: lifecycle
            }
        }else{
            this
        }
    }

    private fun replace(component: ProgramComponent<*>){
        backStack = backStack.removeLast() + component
    }
}