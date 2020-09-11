package com.company.projectName.entity.source

import com.company.projectName.entity.models.SampleModel
import com.company.projectName.entity.models.common.RemoteResult

interface ISampleRemoteRepository {
    suspend fun loadData(): RemoteResult<List<SampleModel>>
}