package com.shushant.astroyoga.onboard.repo

import com.shushant.astroyoga.data.model.CreateUserRequest
import com.shushant.astroyoga.data.model.UserResponse
import com.shushant.astroyoga.data.repo.UserRepository
import com.shushant.astroyoga.network.utils.ASTROYOGA_URL
import com.shushant.astroyoga.network.utils.CREATE_USER
import com.shushant.astroyoga.network.utils.Either
import com.shushant.astroyoga.network.utils.handleRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val client: HttpClient) :
    UserRepository {
    override suspend fun createUser(userRequest: CreateUserRequest): Either<UserResponse> =
        withContext(Dispatchers.IO) {
            handleRequest {
                client.post {
                    url {
                        url(ASTROYOGA_URL + CREATE_USER)
                    }
                    contentType(ContentType.Application.Json)
                    setBody(userRequest)
                }.body()
            }
        }
}