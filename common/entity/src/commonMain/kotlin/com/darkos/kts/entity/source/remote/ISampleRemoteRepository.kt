package com.darkos.kts.entity.source.remote

import com.darkos.kts.entity.models.SampleModel
import com.darkos.kts.entity.models.common.RemoteResult

interface ISampleRemoteRepository {
    suspend fun loadData(): RemoteResult<List<SampleModel>>
}