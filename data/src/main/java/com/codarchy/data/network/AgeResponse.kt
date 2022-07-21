package com.codarchy.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgeResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "age") val age: Int?,
    @Json(name = "count") val count: Long?
)