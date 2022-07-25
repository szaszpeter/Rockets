package com.codarchy.domain.rocketselection

import com.codarchy.data.model.Rocket
import com.codarchy.data.repository.RocketRepository
import javax.inject.Inject

class RetrieveSelectedRocketUseCase @Inject constructor(private val repository: RocketRepository) {

    operator fun invoke(): Rocket? {
        return repository.savedRocket
    }
}