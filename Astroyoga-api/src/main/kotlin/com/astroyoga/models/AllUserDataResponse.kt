package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class AllUserDataResponse(
    override val status: Boolean,
    override val message: String,
    val data: List<User?> = emptyList()
) : Response {

    companion object {

        fun failed(message: String) = AllUserDataResponse(
            false,
            message
        )

        fun unauthorized(message: String) = AllUserDataResponse(
            false,
            message
        )

        fun success(data: List<User?>, message: String) = AllUserDataResponse(
            true,
            message,
            data
        )

    }
}