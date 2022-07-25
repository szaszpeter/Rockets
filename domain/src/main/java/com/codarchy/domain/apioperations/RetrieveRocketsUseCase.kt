package com.codarchy.domain.apioperations

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Rocket
import com.codarchy.data.repository.RocketRepository
import javax.inject.Inject

class RetrieveRocketsUseCase @Inject constructor(private val repository: RocketRepository){
    suspend operator fun invoke(): ResultWrapper<List<Rocket>> {
        return repository.requestRocketList()
    }
}
