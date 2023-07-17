package com.astroyoga.models

import io.ktor.server.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val gender: String,
    val deviceId: String? = "",
    val username: String,
    val sentimentalStatus: String,
    val dob: String,
    val tob: String,
    val pob: String?,
    val handReadingData: String?,
    val filledIndex: Int?,
    val zodiacSign: String?,
    val horoscope: HoroscopeResponse? = null
) : Principal


@Serializable
data class FirebaseUser(
    val id: String,
    val displayName: String
) : Principal




