package com.shushant.astroyoga.onboard.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.onboard.navigation.OnBoarding
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.Graph
import com.shushant.resource.AppResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    composeNavigator: AppComposeNavigator,
    preferences: PrefStorage
) {
    val scale = remember {
        Animatable(0f)
    }

    val coroutineScope = rememberCoroutineScope()

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        coroutineScope.launch {
            preferences.getUserState.collectLatest { hasUserData ->
                if (hasUserData.isNotEmpty()) {
                    composeNavigator.navigateAndClearBackStack(
                        OnBoarding.AnalyzeDetails.name,
                        Graph.SPLASH
                    )
                } else {
                    composeNavigator.navigateAndClearBackStack(Graph.ONBOARDING, Graph.SPLASH)
                }
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = AppResource.ASTRO_BACKGROUND.drawable),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = AppResource.ASTRO_LOGO.drawable),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value)
                .size(200.dp)
        )
    }
}