package com.shushant.astroyoga.data.repo

import com.shushant.astroyoga.data.model.CreateUserRequest
import com.shushant.astroyoga.data.model.UserResponse
import com.shushant.astroyoga.network.utils.Either

interface UserRepository {
    suspend fun createUser(userRequest: CreateUserRequest): Either<UserResponse>
}