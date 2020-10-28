package com.darkos.kts.domain.repository

import com.darkos.kts.domain.model.response.SampleResponse
import com.darkos.kts.domain.repository.base.BaseRepository
import com.darkos.kts.entity.models.SampleModel
import com.darkos.kts.entity.models.common.RemoteResult
import com.darkos.kts.entity.models.common.mapAsList
import com.darkos.kts.entity.source.remote.ISampleRemoteRepository
import io.ktor.client.request.*

class SampleRepository : BaseRepository(), ISampleRemoteRepository {

    override suspend fun loadData(): RemoteResult<List<SampleModel>> {
        return execute {
            it.get<List<SampleResponse>>("${baseURL}/posts")
        }.mapAsList {
            SampleModel(data = it.title)
        }
    }
}