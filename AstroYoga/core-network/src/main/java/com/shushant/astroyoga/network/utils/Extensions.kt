package com.shushant.astroyoga.network.utils

import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton

@Singleton
val json = Json { encodeDefaults = true }

const val HOROSCOPE = "horoscope"
const val LOCATIONS = "https://nominatim.openstreetmap.org/"
const val ASTROYOGA_URL = "http://URL/v1/"
const val CREATE_USER = "create"


suspend fun <T : Any> handleRequest(requestFunc: suspend () -> T): Either<T> {
    return try {
        val resposne = requestFunc.invoke()
        Either.success(resposne)
    } catch (he: Exception) {
        Timber.e(he.localizedMessage)
        Either.error(he.message ?: "")
    }
}