package com.shushant.chattiez.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shushant.chattiez.data.base.SnackBarState
import com.shushant.chattiez.ui.MainViewModel
import com.shushant.navigation.ChattiezScreens
import kotlin.reflect.KFunction1

@Composable
fun ChattiezNavHost(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier,
) {
    val composeNavigator = mainViewModel.navigator
    val authState by mainViewModel.getAuthState().collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = ChattiezScreens.Splash.route,
        modifier = modifier.navigationBarsPadding()
    ) {
        chattiezSplash(
            composeNavigator = composeNavigator,
            authState = authState
        )
        chattiezOnBoarding(
            composeNavigator = composeNavigator
        )
        chattiezAuthFlow(
            composeNavigator = composeNavigator,
        )
    }
}
