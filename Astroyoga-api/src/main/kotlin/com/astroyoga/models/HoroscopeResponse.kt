package com.astroyoga.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class HoroscopeResponse(
    val color: String? = null, // Gold
    val compatibility: String? = null, // Pisces
    @SerializedName("current_date")
    val currentDate: String? = null, // January 28, 2023
    @SerializedName("date_range")
    val dateRange: String? = null, // Mar 21 - Apr 20
    val description: String? = null, // Feel free to ask the Powers That Be for any favor you know you deserve. First off, they'll know it, too, and they'll be happy to tack on a few extra rewards you'd never have dreamed of asking for.
    @SerializedName("lucky_number")
    val luckyNumber: String? = null, // 55
    val deviceId: String? = null, // 55
    @SerializedName("lucky_time")
    val luckyTime: String? = null, // 11pm
    val mood: String? = null, // Successful
    val time: String? = LocalDateTime.now().toString()
)