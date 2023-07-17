package com.shushant.astroyoga.horoscope.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shushant.astroyoga.horoscope.ui.HoroscopeScreen
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.destinations.BottomNavItem


fun NavController.toHoroScope(navOptions: NavOptions? = null) {
    this.navigate(BottomNavItem.Horoscope.screen_route, navOptions)
}

fun NavGraphBuilder.horoscopeScreen(composeNavigator: AppComposeNavigator) {
    composable(route = BottomNavItem.Horoscope.screen_route) {
        HoroscopeScreen(composeNavigator)
    }
}