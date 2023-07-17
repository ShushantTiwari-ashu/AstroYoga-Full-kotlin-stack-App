package com.shushant.astroyoga.data.model


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class HoroscopeResponse(
    @SerialName("color")
    val color: String? = null, // Gold
    @SerialName("compatibility")
    val compatibility: String? = null, // Pisces
    @SerialName("current_date")
    val currentDate: String? = null, // January 28, 2023
    @SerialName("date_range")
    val dateRange: String? = null, // Mar 21 - Apr 20
    @SerialName("description")
    val description: String? = null, // Feel free to ask the Powers That Be for any favor you know you deserve. First off, they'll know it, too, and they'll be happy to tack on a few extra rewards you'd never have dreamed of asking for.
    @SerialName("lucky_number")
    val luckyNumber: String? = null, // 55
    @SerialName("lucky_time")
    val luckyTime: String? = null, // 11pm
    @SerialName("mood")
    val mood: String? = null, // Successful
    val time: String? = ""
)