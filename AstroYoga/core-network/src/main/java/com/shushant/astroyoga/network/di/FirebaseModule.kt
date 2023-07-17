package com.shushant.astroyoga.network.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.storage.ktx.storage
import com.shushant.astroyoga.network.client.AccountService
import com.shushant.astroyoga.network.client.AccountServiceImpl
import com.shushant.astroyoga.network.utils.CustomHttpLogger
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import timber.log.Timber

val networkModule = module {
    single<AccountService> { AccountServiceImpl(get()) }
    single { Firebase.auth }
    single { Firebase.database }
    single { Firebase.storage }
    single { Firebase.firestore }
    single { Firebase.messaging }
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = CustomHttpLogger()
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Timber.d("HTTP status:", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}