package com.codarchy.domain.rocketselection

import com.codarchy.data.model.Rocket
import com.codarchy.data.repository.RocketRepository
import javax.inject.Inject

class SaveSelectedRocketUseCase @Inject constructor(private val repository: RocketRepository) {

    operator fun invoke(rocket: Rocket?) {
        repository.savedRocket = rocket
    }
}