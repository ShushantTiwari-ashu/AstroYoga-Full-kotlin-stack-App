package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    override val status: Boolean,
    override val message: String,
    val data: User? = null
) : Response {

    companion object {

        fun failed(message: String) = UserDataResponse(
            false,
            message
        )

        fun unauthorized(message: String) = UserDataResponse(
            false,
            message
        )

        fun success(data: User?, message: String) = UserDataResponse(
            true,
            message,
            data
        )

    }
}