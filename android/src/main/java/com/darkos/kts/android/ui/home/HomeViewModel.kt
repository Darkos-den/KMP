package com.darkos.kts.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darkos.kts.domain.feature.home.HomeMessage
import com.darkos.kts.domain.feature.home.HomeState
import com.darkos.kts.domain.feature.home.homeReducer
import com.darkos.kts.entity.models.SampleModel
import com.darkos.core.presentation.viewModel.BaseViewModelImpl
import com.darkos.mvu.Component
import com.darkos.mvu.EffectHandler
import com.darkos.mvu_program.Program

class HomeViewModel(
    effectHandler: EffectHandler
) : BaseViewModelImpl(), Component<HomeState> {

    private val stateSource = MutableLiveData<HomeState>()
    val state: LiveData<HomeState>
        get() = stateSource

    private val program = Program(
        initialState = HomeState.Initial,
        effectHandler = effectHandler,
        reducer = homeReducer,
        component = this
    ).also {
        it.start()
    }

    override fun onCleared() {
        program.clear()
        super.onCleared()
    }

    override fun render(state: HomeState) {
        stateSource.postValue(state)
    }

    fun itemClick(item: SampleModel) {
        program.accept(HomeMessage.ItemClick(item))
    }

    fun reloadContent() {
        program.accept(HomeMessage.ReloadContent)
    }
}