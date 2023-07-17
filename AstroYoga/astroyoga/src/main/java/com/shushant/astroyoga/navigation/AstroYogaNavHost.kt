package com.shushant.astroyoga.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.Graph

@Composable
fun AstroYogaNavHost(
    modifier: Modifier,
    composeNavigator: AppComposeNavigator,
    navHostController: NavHostController,
    preferences: PrefStorage,
) {

    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH,
        modifier = modifier.navigationBarsPadding()
    ) {
        splashGraph(
            composeNavigator = composeNavigator,
            preferences = preferences,
        )
        onBoardingGraph(
            composeNavigator = composeNavigator
        )
        homeGraph(composeNavigator = composeNavigator)
    }
}
