package com.astroyoga.service

import com.astroyoga.models.HoroscopeResponse
import com.astroyoga.utils.HOROSCOPE
import com.astroyoga.utils.client
import com.astroyoga.utils.handleRequest
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoroscopeServiceImpl : HoroscopeService {
    override suspend fun getHoroscope(zodiac: String, day: String): HoroscopeResponse {
        return withContext(Dispatchers.IO) {
            handleRequest {
                client.post {
                    url(HOROSCOPE)
                    url {
                        parameters.append("sign", zodiac)
                        parameters.append("day", day)
                    }
                }.body()
            }
        }
    }
}