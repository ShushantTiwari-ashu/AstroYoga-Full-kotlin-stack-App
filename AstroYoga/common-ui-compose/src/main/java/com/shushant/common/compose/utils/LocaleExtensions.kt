@file:Suppress("DEPRECATION")

package com.shushant.common.compose.utils

import android.content.Context
import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

val Context.primaryLocale: Locale
    get() =
        resources.configuration.locales[0]


internal fun Float.roundTo(n: Int): Float {
    return try {
        "%.${n}f".format(Locale.US, this).toFloat()
    } catch (e: NumberFormatException) {
        this
    }
}

val Int.minutes get() = (this / 60).toString().padStart(2, '0')

val Int.seconds get() = (this % 60).toString().padStart(2, '0')

fun String?.getGreetingMessage(): String {
    val c = Calendar.getInstance()
    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Morning, \n$this"
        in 12..15 -> "Afternoon, \n$this"
        in 16..20 -> "Evening, \n$this"
        in 21..23 -> "Night, \n$this"
        else -> this ?: ""
    }
}

fun String.toDOB(): String {
    val date = LocalDate.parse(this)
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return date.format(formatter)
}