package com.codarchy.data.network

import com.codarchy.data.model.LaunchQuery
import com.codarchy.data.model.RocketOptions
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RocketApi {

    @GET("/v4/rockets/")
    suspend fun getRocketList(): List<RocketResponse>

    @POST("/v5/launches/query")
    suspend fun getLaunchesByRocketId(@Body query: LaunchQuery): LaunchResponseWrapper
}
