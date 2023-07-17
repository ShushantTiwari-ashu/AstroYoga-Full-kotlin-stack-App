package com.astroyoga.database

import com.astroyoga.models.HoroscopeResponse
import com.astroyoga.models.User

interface UserDaoFascade {
    suspend fun getAllUsers(): List<User?>
    suspend fun getUserById(id: String): User?
    suspend fun createUser(user: User): User?
    suspend fun getTodayHoroscope(deviceId: String): HoroscopeResponse?
    suspend fun createHoroscope(horoscopeResponse: HoroscopeResponse): HoroscopeResponse?
    suspend fun updateHoroscope(horoscopeResponse: HoroscopeResponse): HoroscopeResponse?
}