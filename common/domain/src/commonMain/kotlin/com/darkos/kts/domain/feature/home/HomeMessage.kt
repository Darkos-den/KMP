package com.darkos.kts.domain.feature.home

import com.darkos.kts.entity.models.SampleModel
import com.darkos.mvu.models.Message

sealed class HomeMessage: Message() {
    object ReloadContent: HomeMessage()
    class ItemClick(val item: SampleModel): HomeMessage()
}