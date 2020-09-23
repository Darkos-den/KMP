package com.company.projectName.domain.repository

import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.models.common.map
import com.company.projectName.entity.models.dto.LoginDTO
import com.company.projectName.entity.models.dto.TokenDTO
import com.company.projectName.login.LoginRemote
import io.ktor.client.request.*

class LoginRepository : BaseRepository(), LoginRemote<LoginDTO, TokenDTO> {

    override suspend fun login(data: LoginDTO): RemoteResult<TokenDTO> {
        return execute {
            it.get<String>("${baseURL}/posts")
        }.map {
            TokenDTO("token")
        }
    }
}