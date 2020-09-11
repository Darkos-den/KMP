package com.company.projectName.domain.feature.home

import com.company.projectName.domain.model.effect.SampleEffect
import com.company.projectName.domain.model.message.SampleMessage
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val homeReducer = reducer<HomeState> { state, message ->
    when(message){
        is HomeMessage.ItemClick -> {
            TODO()
        }
        is HomeMessage.ReloadContent -> {
            TODO()
        }
        is ComponentInitialized -> {
            StateCmdData(
                state = state,
                effect = SampleEffect.LoadContent
            )
        }
        is SampleMessage.SampleResultSuccess -> {
            TODO()
        }
        is SampleMessage.LoadError -> {
            TODO()
        }
    }

    StateCmdData(
        state = state,
        effect = None()
    )
}