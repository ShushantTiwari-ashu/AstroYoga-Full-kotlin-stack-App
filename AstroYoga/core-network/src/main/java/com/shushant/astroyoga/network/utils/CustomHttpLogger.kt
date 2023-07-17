package com.shushant.astroyoga.network.utils

import io.ktor.client.plugins.logging.*
import timber.log.Timber

class CustomHttpLogger : Logger {
    override fun log(message: String) {
        Timber.d("ASTROYOGA", message)
    }
}