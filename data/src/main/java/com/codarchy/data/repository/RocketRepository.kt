package com.codarchy.data.repository

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.errorhandling.safeApiCall
import com.codarchy.data.mapping.toRocket
import com.codarchy.data.model.Rocket
import com.codarchy.data.network.RocketApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RocketRepository @Inject constructor(private val api: RocketApi) {
    suspend fun requestRocketList(): ResultWrapper<List<Rocket>> = safeApiCall {
        api.getRocketList()
            .map { it.toRocket() }
    }
}
