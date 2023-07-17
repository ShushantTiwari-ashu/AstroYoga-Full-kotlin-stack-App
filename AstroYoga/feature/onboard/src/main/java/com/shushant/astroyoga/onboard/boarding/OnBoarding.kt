package com.shushant.astroyoga.onboard.boarding

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.ChattiezScreens
import com.shushant.resource.AppResource
import kotlinx.coroutines.delay

@Composable
fun OnBoarding(composeNavigator: AppComposeNavigator) {
    val moveScreen = remember {
        mutableStateOf(1)
    }
    LaunchedEffect(Unit) {
        repeat(2) {
            delay(1500L)
            moveScreen.value = moveScreen.value + 1
        }
    }

    when (moveScreen.value) {
        1 -> {
            OnBoardingScreen(
                bgImage = AppResource.BG_ON_BOARDING1,
                illustration = AppResource.ILLUSTRATION,
                title = stringResource(R.string.onboarding_title),
                subtitle = stringResource(R.string.onboarding_subtitle),
                value = moveScreen.value
            ) {
                //composeNavigator.navigateAndClearBackStack(ChattiezScreens.AuthFlow.route)
            }
        }
        2 -> {
            OnBoardingScreen(
                bgImage = AppResource.BG_ON_BOARDING1,
                illustration = AppResource.ILLUSTRATION,
                title = stringResource(R.string.stay_connected),
                subtitle = stringResource(R.string.subtitle2),
                value = moveScreen.value
            ) {
                //composeNavigator.navigateAndClearBackStack(ChattiezScreens.AuthFlow.route)
            }
        }
        else -> {
            //composeNavigator.navigateAndClearBackStack(ChattiezScreens.AuthFlow.route)
        }
    }
}
