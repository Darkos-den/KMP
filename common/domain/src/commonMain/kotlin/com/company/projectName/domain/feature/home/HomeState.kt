package com.company.projectName.domain.feature.home

import com.company.projectName.entity.models.SampleModel
import com.darkos.mvu.models.MVUState

sealed class HomeState : MVUState() {
    abstract val showProgress: Boolean

    object Initial : HomeState() {
        override val showProgress = true
    }

    data class Empty(
        override val showProgress: Boolean
    ) : HomeState()

    data class Data(
        val list: List<SampleModel>,
        override val showProgress: Boolean
    ) : HomeState()
}