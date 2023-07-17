package com.astroyoga

import com.astroyoga.database.DatabaseFactory
import com.astroyoga.plugins.configureFirebaseAuth
import com.astroyoga.plugins.configureMonitoring
import com.astroyoga.plugins.configureRouting
import com.astroyoga.plugins.configureSerialization
import com.astroyoga.service.FirebaseAdmin
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    FirebaseAdmin.init()
    configureFirebaseAuth()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
