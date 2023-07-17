package com.shushant.astroyoga.compatibility.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shushant.astroyoga.compatibility.ui.ProfileScreen
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.destinations.BottomNavItem


fun NavController.toProfileScreen(navOptions: NavOptions? = null) {
    this.navigate(BottomNavItem.Profile.screen_route, navOptions)
}

fun NavGraphBuilder.profileScreen(composeNavigator: AppComposeNavigator) {
    composable(route = BottomNavItem.Profile.screen_route) {
        ProfileScreen(composeNavigator)
    }
}