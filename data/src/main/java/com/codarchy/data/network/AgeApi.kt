package com.codarchy.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface AgeApi {

    @GET(".")
    suspend fun getAgeEstimation(@Query("name") name: String): AgeResponse
}
