package com.shushant.astroyoga.horoscope.ui

import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.base.State
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.model.UserResponse
import com.shushant.astroyoga.network.utils.json
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString

class HoroscopeViewModel(
    private val prefStorage: PrefStorage
) : BaseViewModel<HoroscopeState>(HoroscopeState()) {
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            prefStorage.getUserState.first().whatIfNotNullOrEmpty {
                val data = json.decodeFromString<UserResponse>(it)
                setState { state -> state.copy(loading = false, userResponse = data.data) }
            }
        }
    }

    fun getZodiacSign() = state.value.userResponse?.zodiacSign.toString()
}


data class HoroscopeState(
    val loading: Boolean = false, val userResponse: UserResponse.Data? = null
) : State