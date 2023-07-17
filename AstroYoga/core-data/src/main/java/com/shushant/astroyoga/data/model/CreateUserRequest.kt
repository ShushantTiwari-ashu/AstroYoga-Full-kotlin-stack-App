package com.shushant.astroyoga.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CreateUserRequest(
    @SerialName("dob")
    val dob: String? = null,
    @SerialName("filledIndex")
    val filledIndex: Int? = null, // 0
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("handReadingData")
    val handReadingData: String? = null, // Male
    @SerialName("pob")
    val pob: String? = null,
    @SerialName("sentimentalStatus")
    val sentimentalStatus: String? = null,
    @SerialName("tob")
    val tob: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("zodiacSign")
    val zodiacSign: String? = null
)