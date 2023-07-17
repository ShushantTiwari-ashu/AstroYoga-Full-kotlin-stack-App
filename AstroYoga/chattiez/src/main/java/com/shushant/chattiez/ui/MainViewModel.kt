package com.shushant.chattiez.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shushant.chattiez.data.base.BaseViewModel
import com.shushant.chattiez.data.datastore.PrefStorage
import com.shushant.chattiez.data.utils.NetworkMonitor
import com.shushant.chattiez.network.client.AccountService
import com.shushant.chattiez.splash.ui.SplashUiState
import com.shushant.navigation.AppComposeNavigator
import com.shushant.resource.AppResource
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
        setState { state -> state.copy(background = AppResource.BG, logo = AppResource.LOGO) }
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


    fun isDarkTheme() = preferences.getThemeState

    fun getAuthState() = accountService.getAuthState(viewModelScope)

    val isEmailVerified get() = accountService.currentUser?.isEmailVerified ?: false

    override fun dispose() {
        job.cancel()
    }
}