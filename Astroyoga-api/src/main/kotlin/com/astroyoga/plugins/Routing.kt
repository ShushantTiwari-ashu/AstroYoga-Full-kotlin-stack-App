package com.astroyoga.plugins

import com.astroyoga.database.UserDaoFascadeImpl
import com.astroyoga.models.FirebaseUser
import com.astroyoga.routes.horoscope
import com.astroyoga.routes.users
import com.astroyoga.service.FIREBASE_AUTH
import com.astroyoga.service.HoroscopeServiceImpl
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    val daoFascadeImpl = UserDaoFascadeImpl()
    val horoscopeService = HoroscopeServiceImpl()
    routing {
        get("/") {
            call.respondText {
                "Hell0 astroyoga"
            }
        }
        users(daoFascadeImpl, horoscopeService)
        horoscope(daoFascadeImpl, horoscopeService)
        authenticatedRoute()
    }
}


fun Route.authenticatedRoute() {
    authenticate(FIREBASE_AUTH) {
        get("/authenticated") {
            val user: FirebaseUser =
                call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            call.respond("User is authenticated: $user")
        }
    }
}