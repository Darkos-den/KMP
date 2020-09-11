package com.company.projectName.domain.feature.home

import com.company.projectName.domain.common.updateWithoutCmd
import com.company.projectName.domain.feature.login.createException
import com.company.projectName.domain.model.effect.GeneralEffect
import com.company.projectName.domain.model.effect.SampleEffect
import com.company.projectName.domain.model.message.SampleMessage
import com.darkos.mvu.models.ComponentInitialized
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.reducer

val homeReducer = reducer<HomeState> { state, message ->
    when (message) {
        is HomeMessage.ItemClick -> {
            TODO()
        }
        is HomeMessage.ReloadContent -> {
            StateCmdData(
                state = when (state) {
                    is HomeState.Data -> state.copy(showProgress = true)
                    is HomeState.Empty -> state.copy(showProgress = true)
                    is HomeState.Initial -> throw createException()
                },
                effect = SampleEffect.LoadContent
            )
        }
        is ComponentInitialized -> {
            StateCmdData(
                state = state,
                effect = SampleEffect.LoadContent
            )
        }
        is SampleMessage.SampleResultSuccess -> {
            StateCmdData(
                state = if(message.data.isEmpty()){
                    HomeState.Empty(showProgress = false)
                }else{
                    HomeState.Data(list = message.data, showProgress = false)
                },
                effect = None()
            )
        }
        is SampleMessage.LoadError -> {
            StateCmdData(
                state = when (state) {
                    is HomeState.Data -> state.copy(showProgress = false)
                    is HomeState.Empty -> state.copy(showProgress = false)
                    is HomeState.Initial -> HomeState.Empty(showProgress = false)
                },
                effect = GeneralEffect.ShowUserMessage(message.message)
            )
        }
        else -> state.updateWithoutCmd()
    }
}