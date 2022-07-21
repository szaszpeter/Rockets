package com.codarchy.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketOptions(
    @Json(name = "options") val options: Option
)

@JsonClass(generateAdapter = true)
data class Option(
    @Json(name = "pagination") val pagination: Boolean
)

@JsonClass(generateAdapter = true)
data class LaunchQuery(
    @Json(name = "query") val query: Query
)

@JsonClass(generateAdapter = true)
data class Query(
    @Json(name = "rocket") val rocketId: String
)



