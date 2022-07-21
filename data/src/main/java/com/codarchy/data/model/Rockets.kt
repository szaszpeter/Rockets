package com.codarchy.data.model

data class Rockets(val rocketList: List<Rocket> = emptyList())

data class Rocket(
    val id: String,
    val name: String,
    val description: String,
    val country: String,
    val engines: Engines,
    val height: Height,
    val mass: Mass,
    val active: Boolean
)

data class Engines(
    val number: Int,
)

data class Height(
    val meters: String,
)

data class Mass(
    val kg: Double,
)