package com.darkos.kts.remote.repository

import com.darkos.kts.feature.signin.LoginByEmailRemote
import com.darkos.kts.feature.signin.model.dto.LoginDTO
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.kts.remote.model.common.RemoteResult
import com.darkos.kts.remote.model.common.map
import com.darkos.kts.remote.repository.base.BaseRepository
import com.darkos.mvu.login.model.LoginResult
import io.ktor.client.request.*

class LoginRemoteRepository : BaseRepository(), LoginByEmailRemote {

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