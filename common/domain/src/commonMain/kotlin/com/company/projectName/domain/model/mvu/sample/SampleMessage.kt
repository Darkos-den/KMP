package com.company.projectName.domain.model.mvu.sample

import com.company.projectName.entity.models.SampleModel
import com.darkos.mvu.models.Message

sealed class SampleMessage: Message() {
    class SampleResultSuccess(val data: List<SampleModel>): SampleMessage()
    class LoadError(val message: String): SampleMessage()
}