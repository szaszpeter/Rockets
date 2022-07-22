package com.codarchy.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Rocket
import com.codarchy.domain.RetrieveRocketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val retrieveRocketsUseCase: RetrieveRocketsUseCase
): ViewModel(){

    val rawData = mutableListOf<Rocket>()
    val  state = mutableStateOf<RocketListUIState>(Idle)
    val  filterState = mutableStateOf<FilterUIState>(ResetActiveFilter)

    init {
        requestRockets()
    }

    fun requestRockets() {
        viewModelScope.launch {
            state.value = Loading
            withContext(Dispatchers.IO) {
                when(val rocketReponse = retrieveRocketsUseCase()) {
                    is ResultWrapper.NetworkError -> showNetworkError()
                    is ResultWrapper.GenericError -> showGenericError()
                    is ResultWrapper.Success -> {
                        state.value = RocketListReady(rocketReponse.value)
                        rawData.addAll(rocketReponse.value)
                        filterState.value = ResetActiveFilter
                    }
                }
            }
        }
    }

    fun toggleFilter(identifier: String) {
        when(identifier) {
            "inactive" -> {filterState.value = InActiveFilterEnabled(getInactiveRockets())}
            "active" -> {filterState.value = ActiveFilterEnabled(getActiveRockets())}
            "reset" -> {filterState.value = ResetActiveFilter}
        }
    }

    fun getActiveRockets(): List<Rocket> {
        return rawData.filter { it.active }
    }

    fun getInactiveRockets(): List<Rocket> {
        return rawData.filter { !it.active }
    }

    private fun showGenericError() {
        state.value = GenericError
    }

    private fun showNetworkError() {
        state.value = NetworkError
    }
}

sealed class RocketListUIState
data class RocketListReady(val rockets: List<Rocket>) : RocketListUIState()
object Loading : RocketListUIState()
object Idle: RocketListUIState()
object NetworkError: RocketListUIState()
object GenericError: RocketListUIState()


sealed class FilterUIState
data class ActiveFilterEnabled(val filteredRockets: List<Rocket>): FilterUIState()
data class InActiveFilterEnabled(val filteredRockets: List<Rocket>): FilterUIState()
object ResetActiveFilter: FilterUIState()

