package com.codarchy.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RocketApi {

    @GET("/v4/rockets")
    suspend fun getRocketList(): List<RocketResponse>
}
