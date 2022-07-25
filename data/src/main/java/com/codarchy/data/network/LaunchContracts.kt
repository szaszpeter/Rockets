package com.codarchy.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchResponseWrapper(
    @Json(name = "docs") val docs: List<LaunchResponse>,
)

@JsonClass(generateAdapter = true)
data class LaunchResponse(
    @Json(name = "name") val name: String,
    @Json(name = "date_utc") val dateUtc: String,
    @Json(name = "success") val success: Boolean
)