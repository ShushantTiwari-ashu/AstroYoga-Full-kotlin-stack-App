package com.astroyoga.service

import com.astroyoga.models.HoroscopeResponse

interface HoroscopeService {
    suspend fun getHoroscope(zodiac: String, day: String = "today"): HoroscopeResponse
}