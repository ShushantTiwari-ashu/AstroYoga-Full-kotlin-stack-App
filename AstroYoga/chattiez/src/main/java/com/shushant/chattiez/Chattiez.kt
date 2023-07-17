package com.shushant.chattiez

import android.app.Application
import com.shushant.chattiez.auth.di.dataModule
import com.shushant.chattiez.data.datastore.datastoreModule
import com.shushant.chattiez.di.appModule
import com.shushant.chattiez.network.di.firebaseModule
import com.shushant.chattiez.operator.GlobalResponseOperator
import com.shushant.navigation.di.navigationModule
import com.skydoves.sandwich.SandwichInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Chattiez : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        SandwichInitializer.sandwichOperators += GlobalResponseOperator<Any>(this)
        startKoin {
            androidLogger()
            androidContext(this@Chattiez)
            modules(
                navigationModule, appModule, datastoreModule, dataModule, firebaseModule,
            )
        }
    }
}