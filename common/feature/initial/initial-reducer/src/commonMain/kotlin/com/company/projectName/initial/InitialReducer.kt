package com.company.projectName.initial

import com.company.projectName.initial.model.mvu.InitialEffect
import com.company.projectName.initial.model.mvu.InitialState
import com.darkos.mvu.Reducer
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData

class InitialReducer: IInitialReducer {

    override fun update(state: InitialState, message: Message): StateCmdData<InitialState> {
        return when(message){
            is ComponentInitialized -> {
                StateCmdData(
                    state = state,
                    effect = InitialEffect.Navigation.NavigateToSplash
                )
            }
            else -> StateCmdData(
                state = state,
                effect = None()
            )
        }
    }
}