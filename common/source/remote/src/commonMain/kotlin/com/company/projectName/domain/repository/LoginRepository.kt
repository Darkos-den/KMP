package com.company.projectName.domain.repository

import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.models.common.map
import com.company.projectName.signin.SignInRemote
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import io.ktor.client.request.*

class LoginRepository : BaseRepository(), SignInRemote {

    override suspend fun login(data: LoginDTO): RemoteResult<TokenDTO> {
        return execute {
            it.get<String>("${baseURL}/posts")
        }.map {
            TokenDTO("token")
        }
    }
}