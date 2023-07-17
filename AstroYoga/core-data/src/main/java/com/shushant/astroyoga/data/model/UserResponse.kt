package com.shushant.astroyoga.data.model


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class UserResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("message")
    val message: String? = null, // Success
    @SerialName("status")
    val status: Boolean? = null // true
) {
    @Keep
    @Serializable
    data class Data(
        @SerialName("deviceId")
        val deviceId: String? = null, // 2e537ce0-70ee-49a4-a55c-0e966984eb4c
        @SerialName("dob")
        val dob: String? = null, // Male
        @SerialName("filledIndex")
        val filledIndex: Int? = null, // 0
        @SerialName("gender")
        val gender: String? = null, // Male
        @SerialName("handReadingData")
        val handReadingData: String? = null, // Male
        @SerialName("pob")
        val pob: String? = null, // Male
        @SerialName("sentimentalStatus")
        val sentimentalStatus: String? = null, // Male
        @SerialName("tob")
        val tob: String? = null, // Male
        @SerialName("username")
        val username: String? = null, // Male
        @SerialName("zodiacSign")
        val zodiacSign: String? = null,
        val horoscope: HoroscopeResponse? = null
    )
}