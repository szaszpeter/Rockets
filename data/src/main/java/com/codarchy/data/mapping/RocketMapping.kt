package com.codarchy.data.mapping

import com.codarchy.data.model.Rocket
import com.codarchy.data.model.Rockets
import com.codarchy.data.network.*

//fun RocketsResponse.toRocketList(): Rockets = Rockets(rocketList.map { it.toRocket() })



fun RocketResponse.toRocket(): Rocket = Rocket(
    id,
    name,
    description,
    country,
    engines.toEngines(),
    height.toHeight(),
    mass.toMass(),
    active
)


private fun Engines.toEngines(): com.codarchy.data.model.Engines = com.codarchy.data.model.Engines(
    number
)

private fun Height.toHeight(): com.codarchy.data.model.Height = com.codarchy.data.model.Height(
    meters
)

private fun Mass.toMass(): com.codarchy.data.model.Mass = com.codarchy.data.model.Mass(
    kg
)

