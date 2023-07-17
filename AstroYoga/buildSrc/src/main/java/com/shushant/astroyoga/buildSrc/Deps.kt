/*
 * Copyright (c) 2023 Shushant tiwari.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.shushant.astroyoga.buildSrc

object Deps {

    object Kotlin {
        private const val version = "1.8.0"

        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"

        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
        const val serializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"

        private const val coroutinesVersion = "1.6.4"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Android {
        private const val gradleVersion = "7.4.0"

        const val gradle = "com.android.tools.build:gradle:$gradleVersion"

        const val desugaring = "com.android.tools:desugar_jdk_libs:1.1.5"

        const val activityVersion = "1.6.0"
        const val activityCompose = "androidx.activity:activity-compose:$activityVersion"

        private const val navigationVersion = "2.5.0-alpha04"

        //        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-beta01"
        const val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"
        const val navigationHiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"

        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"
        const val splashScreen = "androidx.core:core-splashscreen:1.0.0"

        object Compose {
            const val version = "1.4.0-alpha01"
            const val compilerVersion = "1.4.0"
            const val composeMaterialVersion = "1.0.1"
            const val composeRuntimeVersion = "1.3.3"

            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val composeRuntime = "androidx.compose.runtime:runtime:$composeRuntimeVersion"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material3Design =
                "androidx.compose.material3:material3:$composeMaterialVersion"
            const val material3WindowSize =
                "androidx.compose.material3:material3-window-size-class:$composeMaterialVersion"
            const val materialDesignIcons = "androidx.compose.material:material-icons-core:$version"
            const val materialDesignIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha04"
            const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
            const val activity = "androidx.activity:activity-compose:$activityVersion"
            const val viewModels = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
            const val lifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha04"
            const val paging = "androidx.paging:paging-compose:1.0.0-alpha17"

//            private const val lottieVersion = "4.2.0"
//            const val lottie = "com.airbnb.android:lottie-compose:$lottieVersion"

            const val coil = "io.coil-kt:coil-compose:${Utils.coilVersion}"

            //            const val reorderable = "org.burnoutcrew.composereorderable:reorderable:0.7.0"
            const val collapsingToolbar = "me.onebone:toolbar-compose:2.3.5"
        }

        object Accompanist {
            private const val version = "0.27.0"

            const val insets = "com.google.accompanist:accompanist-insets:$version"
            const val insetsUi = "com.google.accompanist:accompanist-insets-ui:$version"
            const val pager = "com.google.accompanist:accompanist-pager:$version"
            const val pagerIndicators =
                "com.google.accompanist:accompanist-pager-indicators:$version"

            const val permissions = "com.google.accompanist:accompanist-permissions:$version"
            const val placeholder =
                "com.google.accompanist:accompanist-placeholder-material:$version"

            //            const val swiperefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
            const val systemUiController =
                "com.google.accompanist:accompanist-systemuicontroller:$version"
            const val navigationMaterial =
                "com.google.accompanist:accompanist-navigation-material:$version"
            const val navigationFlowlayout =
                "com.google.accompanist:accompanist-flowlayout:$version"
            const val navigationAnimation =
                "com.google.accompanist:accompanist-navigation-animation:$version"

        }

        object Lifecycle {
            private const val version = "2.5.1"
            private const val vmSavedStateVersion = "2.5.1"

            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val vmKotlin = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val vmSavedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:$vmSavedStateVersion"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        }

        object Room {
            private const val roomVersion = "2.4.1"

            const val compiler = "androidx.room:room-compiler:$roomVersion"
            const val runtime = "androidx.room:room-runtime:$roomVersion"
            const val ktx = "androidx.room:room-ktx:$roomVersion"
            const val paging = "androidx.room:room-paging:$roomVersion"
        }

        object Paging {
            private const val version = "3.1.1"

            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }

        object ColorPicker {
            private const val version = "0.5.1"
            const val composeColorPicker =
                "com.godaddy.android.colorpicker:compose-color-picker:$version"
            const val composeColorPickerAndroid =
                "com.godaddy.android.colorpicker:compose-color-picker-android:$version"

        }
    }

    object Utils {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
        const val junit = "junit:junit:4.13.2"
        const val threeTen = "org.threeten:threetenbp:1.5.1"
        private const val version = "1.5.2"
        private const val lottieVersion = "5.2.0"
        private const val cameraxVersion = "1.2.1"
        private const val zxing = "3.5.0"

        // CameraX
        const val cameraLifecycle = "androidx.camera:camera-lifecycle:$cameraxVersion"
        const val cameraVideo = "androidx.camera:camera-video:$cameraxVersion"
        const val cameraView = "androidx.camera:camera-view:$cameraxVersion"
        const val cameraExtension = "androidx.camera:camera-extensions:$cameraxVersion"

        const val coilVersion = "2.0.0-alpha02"
        const val coil = "io.coil-kt:coil:$coilVersion"
        const val coilSvg = "io.coil-kt:coil-svg:$coilVersion"
        const val coilCompose = "io.coil-kt:coil-compose:$coilVersion"
        const val balloon = "com.github.skydoves:balloon-compose:$version"
        const val lottie = "com.airbnb.android:lottie-compose:$lottieVersion"
        const val sandwich = "com.github.skydoves:sandwich:1.3.3"
        const val wheelPicker = "com.github.commandiron:WheelPickerCompose:1.1.9"
        const val googlePlaces = "com.google.android.libraries.places:places:3.0.0"
        const val camposer = "io.github.ujizin:camposer:0.1.0"
        const val zxingLib = "com.google.zxing:core:$zxing"
        const val permission = "dev.shreyaspatil.permission-flow:permission-flow-compose:1.0.0"
    }


    object Dagger {
        private const val version = "2.44"

        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
        const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object LeakCanary {
        private const val version = "2.7"

        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"
    }

    object Koin {
        private const val versionCore = "3.3.2"
        private const val versionCompose = "3.4.1"
        private const val versionKtor = "3.3.0"

        const val koinCore = "io.insert-koin:koin-core:$versionCore"
        const val koinTest = "io.insert-koin:koin-test:$versionCore"
        const val koinTestJunit = "io.insert-koin:koin-test-junit5:$versionCore"

        //Android
        const val koinAndroid = "io.insert-koin:koin-android:$versionCore"
        const val koinAndroidNavigation = "io.insert-koin:koin-androidx-navigation:$versionCore"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:$versionCompose"

        //KTOR
        const val koinKtor = "io.insert-koin:koin-ktor:$versionKtor"
        const val koinKtorLogger = "io.insert-koin:koin-logger-slf4j:$versionKtor"

    }

    object Network {
        private const val okHttp = "4.10.0"
        private const val whatIfVersion = "1.1.1"
        private const val retrofitVersion = "2.9.0"

        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttp"
        const val whatIf = "com.github.skydoves:whatif:$whatIfVersion"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"

    }

    object Modules {
        const val CommonResource = ":common-resource"
        const val CommonUiCompose = ":common-ui-compose"
        const val CoreData = ":core-data"
        const val CoreNetwork = ":core-network"
        const val Navigation = ":navigation"
        const val FeatureOnBoard = ":feature:onboard"
        const val FeatureAuth = ":feature-auth"
        const val FeatureCompatibility = ":feature:compatibility"
        const val FeatureHoroscope = ":feature:horoscope"
        const val FeatureProfile = ":feature:profile"
    }

    object Firebase {
        const val googleService = "com.google.gms:google-services:4.3.13"
        const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.9.2"
        const val firebaseBom = "com.google.firebase:firebase-bom:31.1.1"
        const val auth = "com.google.firebase:firebase-auth"
        const val authKtx = "com.google.firebase:firebase-auth-ktx"
        const val fireStore = "com.google.firebase:firebase-firestore"
        const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"
        const val fireStoreKtx = "com.google.firebase:firebase-firestore-ktx"
        const val storageKtx = "com.google.firebase:firebase-storage-ktx"
        const val inappmessaging = "com.google.firebase:firebase-inappmessaging-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val databaseKtx = "com.google.firebase:firebase-database-ktx"
        const val messagingKtx = "com.google.firebase:firebase-messaging-ktx"
        const val serviceAuth = "com.google.android.gms:play-services-auth:20.4.0"
    }
}