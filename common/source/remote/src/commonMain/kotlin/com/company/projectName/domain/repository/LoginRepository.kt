package com.company.projectName.domain.repository

import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.models.common.map
import com.company.projectName.signin.LoginByEmailRemote
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.login.model.LoginResult
import io.ktor.client.request.*

class LoginRepository : BaseRepository(), LoginByEmailRemote {

    override suspend fun login(data: LoginDTO): LoginResult<TokenDTO> {
        return execute {
            it.get<String>("${baseURL}/posts")
        }.map {
            TokenDTO("token")
        }.let {
            when (it) {
                is RemoteResult.Success -> LoginResult.Success(it.data)
                is RemoteResult.Error -> LoginResult.Error(it.error)
            }
        }
    }
}