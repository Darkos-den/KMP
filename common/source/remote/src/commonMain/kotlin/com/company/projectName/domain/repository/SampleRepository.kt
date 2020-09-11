package com.company.projectName.domain.repository

import com.company.projectName.domain.model.response.SampleResponse
import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.SampleModel
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.source.ISampleRemoteRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class SampleRepository : BaseRepository(), ISampleRemoteRepository {

    override suspend fun loadData(): RemoteResult<List<SampleModel>> {
        return try {
            createClient().use {
                it.get<List<SampleResponse>>("${baseURL}/posts")
            }.map {
                SampleModel(
                    data = it.title
                )
            }.let {
                RemoteResult.Success(it)
            }
        } catch (e: Exception) {
            RemoteResult.Error(e)
        }
    }
}