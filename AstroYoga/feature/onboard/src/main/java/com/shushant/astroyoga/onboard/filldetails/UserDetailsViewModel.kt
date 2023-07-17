package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.astroyoga.network.utils.Either
import com.shushant.astroyoga.onboard.usecase.UserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val useCase: UserDetailsUseCase
) : BaseViewModel<UserDetailsState>(UserDetailsState()) {

    val composableScreens = MutableStateFlow(emptyList<UserScreens>())

    val selectedIndex = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            getUserData()
        }

    }

    private suspend fun getUserData() {
        useCase.getUserFromStorage().collectLatest { userDetailsStateEither ->
            when (userDetailsStateEither) {
                is Either.Error -> setError(userDetailsStateEither.message)
                is Either.Success -> {
                    setState {
                        try {
                            it.copy(loading = false, success = true)
                        } catch (e: Exception) {
                            UserDetailsState()
                        }
                    }
                }
            }
        }
    }

    fun setSelectedIndex(page: Int) {
        selectedIndex.update { page }.also {
            setState { state -> state.copy(filledIndex = page) }.also {
                updatePref()
            }
        }
    }

    fun addComposable(action: List<@Composable (() -> Unit)>) {
        composableScreens.update {
            mutableListOf<UserScreens>().apply {
                action.map {
                    add(UserScreens(action = it))
                }
            }
        }
    }

    fun setUserName(userName: String) {
        setState { state -> state.copy(userName = userName) }.also {
            updatePref()
        }
    }

    fun setGender(gender: Gender) {
        setState { state -> state.copy(gender = gender) }.also {
            updatePref()
        }
    }

    private fun updatePref() {
        viewModelScope.launch {
            useCase.setUserData(state.value)
        }
    }

    fun setSentimentalStatus(sentimentalStatus: SentimentalStatus) {
        setState { state -> state.copy(sentimentalStatus = sentimentalStatus) }.also {
            updatePref()
        }
    }

    fun setDob(dob: String) {
        setState { state -> state.copy(dob = dob) }.also {
            updatePref()
        }
    }

    fun setTob(tob: String) {
        setState { state -> state.copy(tob = tob) }.also {
            updatePref()
        }
    }

    fun setPob(pob: LocationSearchResultItem?) {
        setState { state -> state.copy(pob = pob) }.also {
            updatePref()
        }
    }

    fun createUser() {
        viewModelScope.launch {
            startLoading()
            val request = state.value.toCreateUserRequest()
            when (val result = useCase(request)) {
                is Either.Error -> setError(result.message)
                is Either.Success -> {
                    getUserData()
                }
            }
        }
    }

    private fun setError(message: String) {
        setState { state ->
            state.copy(
                error = message, success = false, loading = false
            )
        }
    }

    private fun startLoading() {
        setState { state ->
            state.copy(
                loading = true
            )
        }
    }
}