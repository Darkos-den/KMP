package com.darkos.projectName.entity.source.remote

import com.darkos.projectName.entity.models.SampleModel
import com.darkos.projectName.entity.models.common.RemoteResult

interface ISampleRemoteRepository {
    suspend fun loadData(): RemoteResult<List<SampleModel>>
}