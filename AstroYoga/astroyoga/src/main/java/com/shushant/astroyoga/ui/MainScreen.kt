package com.shushant.astroyoga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shushant.astroyoga.R
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.utils.NetworkMonitor
import com.shushant.astroyoga.navigation.AstroYogaNavHost
import com.shushant.common.compose.theme.LocalSnackbarHostState
import com.shushant.common.compose.ui.AppBackground
import com.shushant.common.compose.ui.CommonSnackBar
import com.shushant.navigation.AppComposeNavigator

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
)
@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    composeNavigator: AppComposeNavigator,
    preferences: PrefStorage,
    appState: AstroAppState = rememberAstroAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass,
        navigator = composeNavigator,
        preferences = preferences
    )
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val snackBarState by appState.isOffline.collectAsStateWithLifecycle()

    val notConnected = stringResource(id = R.string.not_connected)

    AppBackground {
        LaunchedEffect(snackBarState) {
            if (snackBarState) {
                val result = snackbarHostState.showSnackbar(
                    message = notConnected, duration = SnackbarDuration.Indefinite
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        /* action has been performed */
                    }
                    SnackbarResult.Dismissed -> {
                        /* dismissed, no action needed */
                    }
                }
            }
        }
        CompositionLocalProvider(
            LocalSnackbarHostState provides snackbarHostState
        ) {
            Scaffold(snackbarHost = {
                CommonSnackBar(snackbarHostState = snackbarHostState)
            },
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                bottomBar = {
                    AstroBottomBar(
                        destinations = appState.topLevelDestinations,
                        navigate = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }) { padding ->
                Row(
                    modifier = Modifier
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                        .padding(padding)
                        .consumedWindowInsets(padding)
                        .navigationBarsPadding()
                ) {
                    AstroYogaNavHost(
                        navHostController = appState.navController,
                        modifier = Modifier,
                        composeNavigator = appState.navigator,
                        preferences = appState.preferences
                    )
                }
            }
        }
    }
}



