package com.shushant.astroyoga.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.shushant.astroyoga.data.datastore.AppPreferences
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.utils.ConnectivityManagerNetworkMonitor
import com.shushant.astroyoga.data.utils.NetworkMonitor
import com.shushant.common.compose.theme.AstroYogaTheme
import com.shushant.common.compose.utils.LocalOnFinishDispatcher
import com.shushant.navigation.AppComposeNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val preferences: PrefStorage by inject()
    private val networkMonitor: NetworkMonitor by inject()
    private val composeNavigator: AppComposeNavigator by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CompositionLocalProvider(LocalOnFinishDispatcher provides { finish() }) {
                AstroYogaTheme {
                    MainScreen(
                        preferences = preferences,
                        networkMonitor = networkMonitor,
                        composeNavigator = composeNavigator,
                        windowSizeClass = calculateWindowSizeClass(this)
                    )
                }
            }
        }
    }
}