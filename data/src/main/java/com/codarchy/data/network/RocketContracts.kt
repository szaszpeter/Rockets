package com.codarchy.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "country") val country: String,
    @Json(name = "engines") val engines: Engines,
    @Json(name = "height") val height: Height,
    @Json(name = "mass") val mass: Mass,
    @Json(name = "active") val active: Boolean
)

@JsonClass(generateAdapter = true)
data class Engines(
    @Json(name = "number") val number: Int,
)

@JsonClass(generateAdapter = true)
data class Height(
    @Json(name = "meters") val meters: String,
)

@JsonClass(generateAdapter = true)
data class Mass(
    @Json(name = "kg") val kg: Double,
)