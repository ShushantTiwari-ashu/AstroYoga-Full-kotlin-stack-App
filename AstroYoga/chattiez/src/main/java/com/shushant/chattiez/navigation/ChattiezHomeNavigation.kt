package com.shushant.chattiez.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shushant.chattiez.auth.AuthScreen
import com.shushant.chattiez.auth.activated.AccountActivated
import com.shushant.chattiez.auth.login.LoginScreen
import com.shushant.chattiez.auth.signup.SignUpScreen
import com.shushant.chattiez.data.base.SnackBarState
import com.shushant.chattiez.splash.ui.SplashScreen
import com.shushant.chattiez.splash.boarding.OnBoarding
import com.shushant.chattiez.splash.ui.SplashUiState
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.ChattiezScreens

fun NavGraphBuilder.chattiezSplash(
    composeNavigator: AppComposeNavigator,
    authState: Boolean
) {
    composable(route = ChattiezScreens.Splash.name) {
        SplashScreen(composeNavigator = composeNavigator, isUserSignedOut = authState, splashUiState = SplashUiState())
    }
}

fun NavGraphBuilder.chattiezOnBoarding(
    composeNavigator: AppComposeNavigator
) {
    composable(route = ChattiezScreens.OnBoarding.name) {
        OnBoarding(
            composeNavigator = composeNavigator
        )
    }
}

fun NavGraphBuilder.chattiezAuthFlow(
    composeNavigator: AppComposeNavigator,
) {
    composable(route = ChattiezScreens.AuthFlow.name) {
        AuthScreen(composeNavigator = composeNavigator)
    }

    composable(route = ChattiezScreens.Login.route) {
        LoginScreen(composeNavigator = composeNavigator)
    }
    composable(route = ChattiezScreens.AccountActivated.route) {
        AccountActivated(composeNavigator = composeNavigator)
    }
    composable(route = ChattiezScreens.SignUp.route) {
        SignUpScreen(composeNavigator = composeNavigator)
    }

}