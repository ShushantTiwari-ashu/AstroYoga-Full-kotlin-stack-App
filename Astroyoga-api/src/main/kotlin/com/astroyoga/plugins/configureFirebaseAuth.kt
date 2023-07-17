package com.astroyoga.plugins

import com.astroyoga.models.FirebaseUser
import com.astroyoga.service.FIREBASE_AUTH
import com.astroyoga.service.FirebaseAuthProvider
import com.astroyoga.service.FirebaseConfig
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.AuthenticationConfig

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                // TODO look up user profile from DB
                FirebaseUser(id = it.uid, displayName = it.name.orEmpty())
            }
        }
    }
}


fun AuthenticationConfig.firebase(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(name).apply(configure))
    register(provider)
}