package com.shushant.astroyoga.onboard.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.onboard.filldetails.GetDetailsCarousel
import com.shushant.astroyoga.onboard.filldetails.horoscopeanalyzer.AnalyzeHoroscopeItem
import com.shushant.astroyoga.onboard.onboarding.AstroOnBoarding
import com.shushant.astroyoga.onboard.ui.SplashScreen
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.appendArguments


fun NavGraphBuilder.astroSplash(
    composeNavigator: AppComposeNavigator,
    preferences: PrefStorage,
) {
    composable(route = OnBoarding.Splash.name) {
        SplashScreen(
            composeNavigator = composeNavigator,
            preferences = preferences
        )
    }
}

fun NavGraphBuilder.astroOnBoarding(
    composeNavigator: AppComposeNavigator
) {
    composable(route = OnBoarding.OnBoard.name) {
        AstroOnBoarding(
            composeNavigator
        )
    }
    composable(route = OnBoarding.TakeUserDetails.name) {
        GetDetailsCarousel(
            composeNavigator
        )
    }
    composable(
        route = OnBoarding.AnalyzeDetails.name,
    ) {
        AnalyzeHoroscopeItem(
            composeNavigator
        )
    }
}

sealed class OnBoarding(val route: String, navArguments: List<NamedNavArgument> = emptyList()) {
    val name: String = route.appendArguments(navArguments)

    object Splash : OnBoarding(route = "SPLASH")
    object OnBoard : OnBoarding(route = "ON_BOARD")
    object TakeUserDetails : OnBoarding(route = "TAKE_USER_DETAILS")
    object AnalyzeDetails : OnBoarding(route = "ANALYZE_DETAILS")
}