package com.codarchy.data.network

import com.codarchy.data.model.LaunchQuery
import com.codarchy.data.model.RocketOptions
import retrofit2.http.Body
import retrofit2.http.POST

interface RocketApi {

    @POST("/v4/rockets/query")
    suspend fun getRocketList(@Body options: RocketOptions): List<RocketResponse>

    @POST("/v5/launches/query")
    suspend fun getLaunchesByRocketId(@Body query: LaunchQuery): List<LaunchResponse>
}
