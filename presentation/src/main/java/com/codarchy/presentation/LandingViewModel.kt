package com.codarchy.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.AgeEstimation
import com.codarchy.domain.RetrieveAgeEstimationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val retrieveAgeUseCase: RetrieveAgeEstimationUseCase
): ViewModel(){

    val  state = mutableStateOf<AgeEstimationUIState>(Idle)

    fun searchForAgeEstimation(name: String) {
        viewModelScope.launch {
            state.value = Loading
            withContext(Dispatchers.IO) {
                when(val ageEstimationResponse = retrieveAgeUseCase(name)) {
                    is ResultWrapper.NetworkError -> showNetworkError()
                    is ResultWrapper.GenericError -> showGenericError()
                    is ResultWrapper.Success -> state.value = AgeEstimationReady(ageEstimationResponse.value)
                }
            }
        }
    }

    private fun showGenericError() {
        state.value = GenericError
    }

    private fun showNetworkError() {
        state.value = NetworkError
    }
}

sealed class AgeEstimationUIState
data class AgeEstimationReady(val ageEstimation: AgeEstimation) : AgeEstimationUIState()
object Loading : AgeEstimationUIState()
object Idle: AgeEstimationUIState()
object NetworkError: AgeEstimationUIState()
object GenericError: AgeEstimationUIState()
