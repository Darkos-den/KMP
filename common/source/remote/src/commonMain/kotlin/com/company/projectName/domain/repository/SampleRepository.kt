package com.company.projectName.domain.repository

import com.company.projectName.domain.model.response.SampleResponse
import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.SampleModel
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.models.common.mapAsList
import com.company.projectName.entity.source.ISampleRemoteRepository
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