package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class HoroscopeDataResponse(
    override val status: Boolean,
    override val message: String,
    val data: HoroscopeResponse? = null
) : Response {

    companion object {

        fun failed(message: String) = HoroscopeDataResponse(
            false,
            message
        )

        fun unauthorized(message: String) = HoroscopeDataResponse(
            false,
            message
        )

        fun success(data: HoroscopeResponse?, message: String) = HoroscopeDataResponse(
            true,
            message,
            data
        )
    }
}