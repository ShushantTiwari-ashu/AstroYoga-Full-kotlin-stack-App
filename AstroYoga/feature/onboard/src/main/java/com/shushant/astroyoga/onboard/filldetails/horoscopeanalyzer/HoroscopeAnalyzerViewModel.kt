package com.shushant.astroyoga.onboard.filldetails.horoscopeanalyzer

import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.base.State
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.model.HoroscopeResponse
import com.shushant.astroyoga.data.model.UserResponse
import com.shushant.astroyoga.data.repo.HoroScopeRequest
import com.shushant.astroyoga.data.repo.HoroscopeAnalyzeRepository
import com.shushant.astroyoga.network.utils.Either
import com.shushant.astroyoga.network.utils.json
import com.shushant.astroyoga.onboard.filldetails.UserDetailsState
import com.shushant.astroyoga.onboard.filldetails.getZodiacSign
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import java.time.LocalDate
import java.time.LocalDateTime

class HoroscopeAnalyzerViewModel(
    private val repository: HoroscopeAnalyzeRepository, private val prefStorage: PrefStorage
) : BaseViewModel<HoroScopeState>(HoroScopeState()) {

    init {
        getHoroScope()
    }

    private fun getHoroScope() {
        viewModelScope.launch {
            prefStorage.getTodayHoroscope.first().time.takeIf { it?.isNotEmpty() == true }?.let {
                if (LocalDateTime.parse(prefStorage.getTodayHoroscope.first().time).dayOfMonth < LocalDate.now().dayOfMonth) {
                    fetchData()
                } else {
                    fetchDataFromPrefStorage()
                }
            } ?: kotlin.run {
                fetchData()
            }
        }
    }

    private suspend fun fetchDataFromPrefStorage() {
        prefStorage.getTodayHoroscope.first().let { data ->
            setState { state -> state.copy(success = data) }
        }
    }

    private suspend fun fetchData() {
        prefStorage.getUserState.first().whatIfNotNullOrEmpty { userState ->
            json.decodeFromString<UserResponse>(userState).let { userDetailsState ->
                when (val result = repository.getHoroscope(
                    HoroScopeRequest(
                        deviceId = userDetailsState.data?.deviceId ?: ""
                    )
                )) {
                    is Either.Error -> setState { state -> state.copy(error = result.message) }
                    is Either.Success -> {
                        result.data.whatIfNotNull {
                            it.copy(
                                time = LocalDate.now().toString()
                            ).let { data ->
                                prefStorage.setTodayHoroscope(
                                    data
                                )
                                setState { state -> state.copy(success = data) }
                            }
                        }
                    }
                }
            }
        }
    }

}

data class HoroScopeState(val success: HoroscopeResponse? = null, val error: String = "") : State
