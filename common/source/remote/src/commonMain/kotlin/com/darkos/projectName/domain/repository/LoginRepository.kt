package com.darkos.projectName.domain.repository

import com.darkos.projectName.domain.repository.base.BaseRepository
import com.darkos.projectName.entity.models.common.RemoteResult
import com.darkos.projectName.entity.models.common.map
import com.darkos.projectName.signin.LoginByEmailRemote
import com.darkos.projectName.signin.model.dto.LoginDTO
import com.darkos.projectName.signin.model.dto.TokenDTO
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