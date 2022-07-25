package com.codarchy.presentation_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Launch
import com.codarchy.data.model.Rocket
import com.codarchy.domain.apioperations.RetrieveLaunchesUseCase
import com.codarchy.domain.rocketselection.RetrieveSelectedRocketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    private val retrieveLaunchesUseCase: RetrieveLaunchesUseCase,
    private val retrieveSelectedRocketUseCase: RetrieveSelectedRocketUseCase
) : ViewModel() {

    var selectedRocket: Rocket? = null
    val state = mutableStateOf<RocketDetailsUIState>(Idle)

    init {
        selectedRocket = retrieveSelectedRocketUseCase.invoke()
        requestLaunchInformation()
    }

    private fun requestLaunchInformation() {
        viewModelScope.launch {
            state.value = Loading
            withContext(Dispatchers.IO) {
                when(val launchResponse = retrieveLaunchesUseCase(selectedRocket?.id ?: "")) {
                    is ResultWrapper.NetworkError -> showNetworkError()
                    is ResultWrapper.GenericError -> showGenericError()
                    is ResultWrapper.Success -> {
                        state.value = RocketDetailsReady(launchResponse.value)
                    }
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

sealed class RocketDetailsUIState
data class RocketDetailsReady(val launches: List<Launch>) : RocketDetailsUIState()
object Loading : RocketDetailsUIState()
object Idle: RocketDetailsUIState()
object NetworkError: RocketDetailsUIState()
object GenericError: RocketDetailsUIState()