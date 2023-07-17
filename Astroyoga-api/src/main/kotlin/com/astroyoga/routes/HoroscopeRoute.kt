package com.astroyoga.routes

import com.astroyoga.database.UserDaoFascadeImpl
import com.astroyoga.models.HoroscopeDataResponse
import com.astroyoga.service.HoroscopeServiceImpl
import com.astroyoga.utils.GET_HOROSCOPE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.horoscope(
    db: UserDaoFascadeImpl,
    horoscopeService: HoroscopeServiceImpl,
) {
    get(GET_HOROSCOPE) {
        val deviceId = kotlin.runCatching { call.request.header("device_id") }.getOrElse {
            throw BadRequestException(it.localizedMessage)
        }

        try {
            val newUser = db.getUserById(deviceId ?: "")
            val horoscopeResponse = horoscopeService.getHoroscope(newUser?.zodiacSign ?: "", "today")
            val horoscope = db.updateHoroscope(horoscopeResponse.copy(deviceId = newUser?.deviceId))
            call.respond(HoroscopeDataResponse.success(data = horoscope, "Success"))

        } catch (e: Throwable) {
            application.log.error("Failed to register user", e)
            call.respond(HttpStatusCode.BadRequest, "Problems creating User")
        }
    }

}

