package com.darkos.projectName.domain.model.mvu.sample

import com.darkos.projectName.entity.models.SampleModel
import com.darkos.mvu.models.Message

sealed class SampleMessage: Message() {
    class SampleResultSuccess(val data: List<SampleModel>): SampleMessage()
    class LoadError(val message: String): SampleMessage()
}