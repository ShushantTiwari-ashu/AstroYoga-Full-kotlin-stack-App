/*
 * Copyright (c) 2023 Shushant Tiwari.
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

package com.shushant.astroyoga.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.shushant.astroyoga.data.model.HoroscopeResponse
import com.shushant.astroyoga.network.utils.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class AppPreferences(private val datastoreUtils: DatastoreUtils) :
    PrefStorage {

    companion object {
        val THEME_STATE_KEY = booleanPreferencesKey(name = "theme_state")
        val USER_STATE = stringPreferencesKey(name = "user_state")
        val HOROSCOPE_KEY = stringPreferencesKey(name = "horoscope")
    }

    private suspend fun <T> setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        datastoreUtils.setValue(key, value)
    }

    private suspend fun <T> setValue(
        key: Preferences.Key<String>,
        value: T,
        serializer: KSerializer<T>
    ) =
        datastoreUtils.setValue(key, value, serializer)

    private fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> =
        datastoreUtils.getValue(key, defaultValue)


    fun <T> getValue(
        name: Preferences.Key<String>,
        serializer: KSerializer<T>,
        defaultValue: T
    ): Flow<T> =
        datastoreUtils.getValue(name, serializer, defaultValue)

    override suspend fun setCurrentTheme(isDark: Boolean) {
        setValue(THEME_STATE_KEY, value = isDark)
    }

    override suspend fun setUserData(userState: String) {
        setValue(USER_STATE, value = userState)
    }

    override suspend fun setTodayHoroscope(horoscope: HoroscopeResponse) {
        val string = json.encodeToString(horoscope)
        setValue(HOROSCOPE_KEY, string)
    }

    override val getUserState: Flow<String>
        get() = getValue(USER_STATE, "")
    override val getTodayHoroscope: Flow<HoroscopeResponse>
        get() = getValue(HOROSCOPE_KEY, "").map {
            try {
                json.decodeFromString(it)
            } catch (e: Exception) {
                HoroscopeResponse()
            }
        }

    override val getThemeState: Flow<Boolean>
        get() = getValue(THEME_STATE_KEY, false)
}