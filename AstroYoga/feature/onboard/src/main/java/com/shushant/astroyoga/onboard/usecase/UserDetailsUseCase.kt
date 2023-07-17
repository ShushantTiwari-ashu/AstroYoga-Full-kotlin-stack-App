package com.shushant.astroyoga.onboard.usecase

import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.model.CreateUserRequest
import com.shushant.astroyoga.data.model.UserResponse
import com.shushant.astroyoga.data.repo.UserRepository
import com.shushant.astroyoga.network.utils.Either
import com.shushant.astroyoga.network.utils.json
import com.shushant.astroyoga.onboard.filldetails.UserDetailsState
import com.shushant.astroyoga.onboard.filldetails.mapToUserState
import com.shushant.astroyoga.onboard.filldetails.toCreateUserRequest
import com.shushant.astroyoga.onboard.filldetails.toUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class UserDetailsUseCase(
    private val userRepository: UserRepository, private val prefStorage: PrefStorage
) {

    suspend fun getUserFromStorage(): Flow<Either<UserResponse>> = withContext(Dispatchers.IO) {
        return@withContext prefStorage.getUserState.catch {
            Either.error<UserResponse>(message = "Error in getting user state!")
        }.map {
            Either.success(json.decodeFromString<UserResponse>(it))
        }.catch {
            Either.error<UserResponse>(message = "Error in getting user state!")
        }
    }


    suspend fun setUserData(userDetailsState: UserDetailsState) = withContext(Dispatchers.IO) {
        prefStorage.setUserData(json.encodeToString(userDetailsState.toUserResponse()))
    }


    suspend operator fun invoke(userRequest: CreateUserRequest): Either<UserResponse> {
        return when (val result = userRepository.createUser(userRequest)) {
            is Either.Error -> result
            is Either.Success -> {
                prefStorage.setUserData(json.encodeToString(result.data))
                result.data.data?.horoscope?.let { prefStorage.setTodayHoroscope(it) }
                result
            }
        }
    }
}