package com.shushant.astroyoga.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.shushant.astroyoga.compatibility.navigation.toCompatibilityScreen
import com.shushant.astroyoga.compatibility.navigation.toProfileScreen
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.utils.NetworkMonitor
import com.shushant.astroyoga.horoscope.navigation.toHoroScope
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.destinations.BottomNavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAstroAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    navigator: AppComposeNavigator,
    preferences: PrefStorage,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberAnimatedNavController()
): AstroAppState {
    return remember(
        navController, coroutineScope, windowSizeClass, networkMonitor, navigator, preferences
    ) {
        AstroAppState(
            navController, coroutineScope, windowSizeClass, networkMonitor, navigator, preferences
        )
    }
}

@Stable
class AstroAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    val navigator: AppComposeNavigator,
    val preferences: PrefStorage
) {
    init {
        coroutineScope.launch {
            navigator.handleNavigationCommands(navController)
        }
    }

    val topLevelDestinations = listOf(
        BottomNavItem.Horoscope,
        BottomNavItem.Compatibility,
        BottomNavItem.Profile,
    )

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline.map(Boolean::not).stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    fun navigateToTopLevelDestination(topLevelDestination: BottomNavItem) {
        trace("Navigation: ${topLevelDestination.screen_route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                BottomNavItem.Horoscope -> navController.toHoroScope(
                    topLevelNavOptions
                )
                BottomNavItem.Compatibility -> navController.toCompatibilityScreen(
                    topLevelNavOptions
                )
                BottomNavItem.Profile -> navController.toProfileScreen(
                    topLevelNavOptions
                )
            }
        }
    }
}