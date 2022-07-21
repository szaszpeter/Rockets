package com.codarchy.domain

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Launch
import com.codarchy.data.repository.LaunchRepository
import javax.inject.Inject

class RetrieveLaunchesUseCase @Inject constructor(private val repository: LaunchRepository){
    suspend operator fun invoke(rocketId: String): ResultWrapper<List<Launch>> {
        return repository.requestLaunchByRocketId(rocketId)
    }
}


