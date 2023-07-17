package com.astroyoga.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

object UserTable : Table(name = "User") {

    val filledIndex: Column<Int> = integer("filledIndex")
    val device_id: Column<UUID> = uuid("device_id").uniqueIndex()
    val gender: Column<String> = varchar("gender", 128)
    val username: Column<String> = varchar("username", 128)
    val sentimentalStatus: Column<String> = varchar("sentimentalStatus", 128)
    val dob: Column<String> = varchar("dob", 128)
    val tob: Column<String> = varchar("tob", 128)
    val zodiacSign: Column<String> = varchar("zodiacSign", 128)
    val pob: Column<String> = text("pob")
    val handReadingData: Column<String> = varchar("handReadingData", 64)
}