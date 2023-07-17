package com.shushant.astroyoga.onboard.filldetails.pob

import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.base.State
import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.astroyoga.data.repo.PlaceRepository
import com.shushant.astroyoga.network.utils.Either
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlaceOfBirthViewModel(private val repository: PlaceRepository) :
    BaseViewModel<PlaceOfBirthDataState>(PlaceOfBirthDataState()) {

    private var job: Job? = null

    fun fetchPlaces(searchText: String) {
        job?.cancel()
        job = viewModelScope.launch {
            setLoading(true)
            when (val result = repository.getPlaces(searchText)) {
                is Either.Success -> {
                    setState { state ->
                        state.copy(
                            data = result.data,
                            errorMessage = "",
                            isLoading = false
                        )
                    }
                }
                is Either.Error -> {
                    setState { state ->
                        state.copy(
                            errorMessage = result.message,
                            data = null,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        setState { state -> state.copy(isLoading = isLoading) }
    }

    fun clearData() {
        setState { state -> state.copy(errorMessage = "", data = null) }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}


data class PlaceOfBirthDataState(
    val data: List<LocationSearchResultItem>? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false
) : State