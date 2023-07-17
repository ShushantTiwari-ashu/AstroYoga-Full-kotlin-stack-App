package com.astroyoga.database

import com.astroyoga.database.DatabaseFactory.dbQuery
import com.astroyoga.models.HoroscopeResponse
import com.astroyoga.models.User
import com.astroyoga.table.HoroscopeTable
import com.astroyoga.table.UserTable
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.util.UUID

class UserDaoFascadeImpl : UserDaoFascade {
    override suspend fun getAllUsers(): List<User?> =
        dbQuery {
            val data = UserTable.selectAll().map {
                rowToResult(it)
            }
            data
        }


    override suspend fun getUserById(id: String): User? {
        return dbQuery {
            UserTable.select { UserTable.device_id.eq(UUID.fromString(id)) }.map { rowToResult(it) }
                .singleOrNull()
        }
    }

    override suspend fun createUser(user: User): User? {
        return dbQuery {
            UserTable.insert {
                it[username] = user.username
                it[filledIndex] = user.filledIndex ?: 0
                it[device_id] = UUID.randomUUID()
                it[gender] = user.gender
                it[sentimentalStatus] = user.sentimentalStatus
                it[dob] = user.dob
                it[handReadingData] = user.handReadingData ?: ""
                it[tob] = user.tob
                it[zodiacSign] = user.zodiacSign ?: ""
                it[pob] = user.pob ?: ""
            }.resultedValues?.singleOrNull()?.let(::rowToResult)
        }
    }

    private fun rowToResult(row: ResultRow?): User? {
        if (row == null) return null
        return User(
            deviceId = row[UserTable.device_id].toString(),
            username = row[UserTable.username],
            filledIndex = row[UserTable.filledIndex],
            gender = row[UserTable.gender],
            handReadingData = row[UserTable.handReadingData],
            zodiacSign = row[UserTable.zodiacSign],
            pob = row[UserTable.pob],
            tob = row[UserTable.tob],
            dob = row[UserTable.dob],
            sentimentalStatus = row[UserTable.sentimentalStatus],
        )
    }

    override suspend fun getTodayHoroscope(deviceId: String): HoroscopeResponse? = dbQuery {
        HoroscopeTable.select { HoroscopeTable.device_id.eq(deviceId) }
            .map { rowToResultHoroscopeResponse(it) }.singleOrNull()
    }

    override suspend fun createHoroscope(horoscopeResponse: HoroscopeResponse): HoroscopeResponse? {
        return dbQuery {
            HoroscopeTable.insert {
                it[description] = horoscopeResponse.description ?: ""
                it[compatibility] = horoscopeResponse.compatibility ?: ""
                it[currentDate] = horoscopeResponse.currentDate ?: ""
                it[dateRange] = horoscopeResponse.dateRange ?: ""
                it[luckyNumber] = horoscopeResponse.luckyNumber ?: ""
                it[device_id] = horoscopeResponse.deviceId ?: ""
                it[color] = horoscopeResponse.color ?: ""
                it[luckyTime] = horoscopeResponse.luckyTime ?: ""
                it[mood] = horoscopeResponse.mood ?: ""
                it[time] = horoscopeResponse.time ?: ""

            }.resultedValues?.singleOrNull()?.let(::rowToResultHoroscopeResponse)
        }
    }

    override suspend fun updateHoroscope(horoscopeResponse: HoroscopeResponse): HoroscopeResponse? {
        return dbQuery {
            HoroscopeTable.update(where = {
                HoroscopeTable.device_id eq (horoscopeResponse.deviceId ?: "")
            }) {
                it[description] = horoscopeResponse.description ?: ""
                it[compatibility] = horoscopeResponse.compatibility ?: ""
                it[currentDate] = horoscopeResponse.currentDate ?: ""
                it[dateRange] = horoscopeResponse.dateRange ?: ""
                it[luckyNumber] = horoscopeResponse.luckyNumber ?: ""
                it[device_id] = horoscopeResponse.deviceId ?: ""
                it[color] = horoscopeResponse.color ?: ""
                it[luckyTime] = horoscopeResponse.luckyTime ?: ""
                it[mood] = horoscopeResponse.mood ?: ""
                it[time] = horoscopeResponse.time ?: ""
            }
            runBlocking {
                getTodayHoroscope(horoscopeResponse.deviceId ?: "")
            }
        }
    }

    private fun rowToResultHoroscopeResponse(row: ResultRow?): HoroscopeResponse? {
        if (row == null) return null
        return HoroscopeResponse(
            deviceId = row[HoroscopeTable.device_id].toString(),
            description = row[HoroscopeTable.description].toString(),
            compatibility = row[HoroscopeTable.compatibility].toString(),
            dateRange = row[HoroscopeTable.dateRange].toString(),
            luckyNumber = row[HoroscopeTable.luckyNumber].toString(),
            color = row[HoroscopeTable.color].toString(),
            mood = row[HoroscopeTable.mood].toString(),
            time = row[HoroscopeTable.time].toString(),
            luckyTime = row[HoroscopeTable.luckyTime].toString(),
            currentDate = row[HoroscopeTable.currentDate].toString(),
        )
    }

}