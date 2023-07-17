package com.shushant.resource

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.Month

enum class AppResource(@DrawableRes val drawable: Int) {
    LOGO(R.drawable.logo_mark),
    ASTRO_LOGO(R.drawable.logo_astro_yoga),
    BG(R.drawable.bg_big),
    ASTRO_BACKGROUND(R.drawable.astro_background),
    BG_ON_BOARDING1(R.drawable.bg_onboarding),
    ASTRO_ON_BOARDING(R.drawable.onboarding),
    CHAT_BACK(R.drawable.chat_back),
    BG_SVG(R.drawable.bg),
    ILLUSTRATION(R.drawable.illustration),
    ASTRO_ILLUSTRATION(R.drawable.astro_illustration),
    GOOGLE(R.drawable.google),
    APPLE(R.drawable.apple),
    GITHUB(R.drawable.github),
    FACEBOOK(R.drawable.facebook),
    USERNAME(R.drawable.username),
    EMAIL(R.drawable.email),
    PASSWORD(R.drawable.password),
    PASSWORD_TOGGLE(R.drawable.password_toggle),
    PASSWORD_TOGGLE_OFF(R.drawable.baseline_visibility_off_24),
    GOOGLE_COLOURED(R.drawable.google_colored),
    PAGINATION_1(R.drawable.pagination_1),
    PAGINATION_2(R.drawable.pagination_2),
    GRADIENT_BG(R.drawable.gradient_text),
    ACTIVATED(R.drawable.activated_day),
    ACTIVATED_NIGHT(R.drawable.activated_night),
    TELL_YOUR_NAME(R.drawable.user_name),
    GENDER(R.drawable.tell_gender),
    PALM_READING(R.drawable.palm_reading),
    ENABLE_REMINDER(R.drawable.enable_reminder),
    ANALYZE_SCOPE(R.drawable.analyze_horoscope),
    SENTIMENTAL_STATUS(R.drawable.sentimental_status),
    SELECT_DOB(R.drawable.select_dob),
    SELECT_POB(R.drawable.select_place),
    SELECT_TOB(R.drawable.time_of_birth),
    Compatibility(R.drawable.home_page),
    DASHBOARD(R.drawable.dashboard),
    UNKNOWN(0)
}

fun Boolean.getPasswordToggle() = if (this) AppResource.PASSWORD else AppResource.PASSWORD_TOGGLE

fun String.getZodiacResource():  Int {
    return when (this) {
        "Capricorn" -> {
            R.drawable.capricorn
        }
        "Aquarius" -> {
            R.drawable.aquarius
        }
        "Pisces" -> {
            R.drawable.pisces
        }

        "Taurus" -> {
            R.drawable.taurus
        }
        "Aries" -> {
            R.drawable.aries
        }
        "Gemini" -> {
            R.drawable.gemini
        }
        "Cancer" -> {
            R.drawable.cancer
        }
        "Leo" -> {
            R.drawable.leo
        }

        "Virgo" -> {
            R.drawable.virgo
        }

        "Libra" -> {
            R.drawable.libra
        }
        "Scorpio" -> {
            R.drawable.scorpio
        }
        "Sagittarius" -> {
            R.drawable.sagittarius
        }
        else -> {
            0
        }
    }
}
