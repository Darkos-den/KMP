package com.darkos.kts.feature.initial

import com.darkos.core.presentation.ComponentViewModel
import com.darkos.kts.feature.initial.model.mvu.InitialState

class InitialViewModel(
    effectHandler: IInitialEffectHandler,
    reducer: IInitialReducer
) : ComponentViewModel<InitialState, IInitialEffectHandler, IInitialReducer>(
    effectHandler, reducer
) {

    override val initial = InitialState()
}