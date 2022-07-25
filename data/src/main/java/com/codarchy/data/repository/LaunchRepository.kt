package com.codarchy.data.repository

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.errorhandling.safeApiCall
import com.codarchy.data.mapping.toLaunch
import com.codarchy.data.model.Launch
import com.codarchy.data.model.LaunchQuery
import com.codarchy.data.model.Query
import com.codarchy.data.network.RocketApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchRepository @Inject constructor(private val api: RocketApi) {
    suspend fun requestLaunchByRocketId(rocketId: String): ResultWrapper<List<Launch>> = safeApiCall { api.getLaunchesByRocketId(
        LaunchQuery(Query(rocketId))
    ).docs.map { it.toLaunch() } }
}