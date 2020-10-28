package com.darkos.projectName.domain.repository

import com.darkos.projectName.domain.model.response.SampleResponse
import com.darkos.projectName.domain.repository.base.BaseRepository
import com.darkos.projectName.entity.models.SampleModel
import com.darkos.projectName.entity.models.common.RemoteResult
import com.darkos.projectName.entity.models.common.mapAsList
import com.darkos.projectName.entity.source.remote.ISampleRemoteRepository
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