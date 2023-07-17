package com.shushant.astroyoga.compatibility.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shushant.astroyoga.compatibility.ui.CompatibilityScreen
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.destinations.BottomNavItem


fun NavController.toCompatibilityScreen(navOptions: NavOptions? = null) {
    this.navigate(BottomNavItem.Compatibility.screen_route, navOptions)
}

fun NavGraphBuilder.compatibilityScreen(composeNavigator: AppComposeNavigator) {
    composable(route = BottomNavItem.Compatibility.screen_route) {
        CompatibilityScreen(composeNavigator)
    }
}