package com.astroyoga.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object HoroscopeTable : Table("horoscope") {
    val color: Column<String> = varchar(name = "color", length = 128)
    val device_id: Column<String> = varchar("device_id", 128).uniqueIndex()
    val compatibility: Column<String> = varchar(name = "compatibility", length = 128)
    val currentDate: Column<String> = varchar(name = "currentDate", length = 128)
    val dateRange: Column<String> = varchar(name = "dateRange", length = 128)
    val luckyNumber: Column<String> = varchar(name = "luckyNumber", length = 128)
    val luckyTime: Column<String> = varchar(name = "luckyTime", length = 128)
    val mood: Column<String> = varchar(name = "mood", length = 128)
    val time: Column<String> = varchar(name = "time", length = 128)
    val description: Column<String> = text(name = "description")
}