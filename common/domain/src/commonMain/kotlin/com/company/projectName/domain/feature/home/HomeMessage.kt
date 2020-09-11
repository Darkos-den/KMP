package com.company.projectName.domain.feature.home

import com.company.projectName.entity.models.SampleModel
import com.darkos.mvu.models.Message

sealed class HomeMessage: Message() {
    object ReloadContent: HomeMessage()
    class ItemClick(val item: SampleModel): HomeMessage()
}