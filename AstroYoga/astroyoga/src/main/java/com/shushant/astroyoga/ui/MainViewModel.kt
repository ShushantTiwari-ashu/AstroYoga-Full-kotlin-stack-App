package com.shushant.astroyoga.ui

import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.utils.NetworkMonitor
import com.shushant.astroyoga.network.client.AccountService
import com.shushant.astroyoga.onboard.ui.SplashUiState
import com.shushant.navigation.AppComposeNavigator
import com.shushant.resource.AppResource
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    appComposeNavigator: AppComposeNavigator,
    private val preferences: PrefStorage,
    private val accountService: AccountService,
    networkMonitor: NetworkMonitor
) : BaseViewModel<SplashUiState>(SplashUiState()), DisposableHandle {
    init {
        setSplashState()
    }

    private fun setSplashState() {
        runBlocking {
            val hasUserData = preferences.getUserState.first().isNotEmpty()
            setState { state ->
                state.copy(
                    background = AppResource.ASTRO_BACKGROUND,
                    logo = AppResource.ASTRO_LOGO,
                    hasUserData = hasUserData
                )
            }
        }
    }

    val navigator: AppComposeNavigator = appComposeNavigator

    val isOffline = networkMonitor.isOnline
        .map { boolean ->
            boolean.not()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1_000),
            initialValue = false
        )

    private val job = Job()

    init {
        getAuthState()
    }


    fun setTheme(isDark: Boolean) {
        viewModelScope.launch(job) {
            preferences.setCurrentTheme(isDark)
        }
    }

    val showBottomBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun updateBottomBarState(show: Boolean) = showBottomBar.updateAndGet { show }


    fun isDarkTheme() = preferences.getThemeState

    fun getAuthState() = accountService.getAuthState(viewModelScope)

    val isEmailVerified get() = accountService.currentUser?.isEmailVerified ?: false

    override fun dispose() {
        job.cancel()
    }
}