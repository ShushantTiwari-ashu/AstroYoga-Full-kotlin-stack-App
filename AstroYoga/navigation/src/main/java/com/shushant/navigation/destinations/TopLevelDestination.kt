package com.shushant.navigation.destinations

import com.shushant.navigation.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Horoscope : BottomNavItem("Horoscope", R.drawable.icon_horoscope, "home")
    object Compatibility :
        BottomNavItem("Compatibility", R.drawable.icon_compatible, "compatibility")

    object Profile : BottomNavItem("Profile", R.drawable.baseline_person_24, "profile")
}