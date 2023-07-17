@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.astroyoga.routes

import com.astroyoga.database.UserDaoFascadeImpl
import com.astroyoga.models.AllUserDataResponse
import com.astroyoga.models.User
import com.astroyoga.models.UserDataResponse
import com.astroyoga.service.HoroscopeServiceImpl
import com.astroyoga.utils.CREATE
import com.astroyoga.utils.GET_USER
import com.astroyoga.utils.USER
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


@Location(CREATE)
class UserCreateRoute

@Location(USER)
class UserRoute

fun Route.users(
    db: UserDaoFascadeImpl,
    horoscopeService: HoroscopeServiceImpl,
) {
    post(CREATE) {
        val user = kotlin.runCatching { call.receive<User>() }.getOrElse {
            throw BadRequestException(it.localizedMessage)
        }
        try {
            val newUser = db.createUser(user)
            val horoscopeResponse =
                horoscopeService.getHoroscope(newUser?.zodiacSign ?: "", "today")
            val horoscope = db.createHoroscope(horoscopeResponse.copy(deviceId = newUser?.deviceId))
            newUser?.let {
                call.respond(UserDataResponse.success(it.copy(horoscope = horoscope), "Success"))
            }
        } catch (e: Throwable) {
            application.log.error("Failed to register user", e)
            call.respond(HttpStatusCode.BadRequest, "Problems creating User")
        }
    }

    get(GET_USER) {

        val users = kotlin.runCatching {
            db.getAllUsers()
        }.getOrElse {
            throw BadRequestException(it.localizedMessage)
        }
        call.respond(AllUserDataResponse.success(users, "Success"))
    }

}

