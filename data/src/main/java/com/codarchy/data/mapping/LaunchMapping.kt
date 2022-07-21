package com.codarchy.data.mapping

import com.codarchy.data.model.Launch
import com.codarchy.data.network.LaunchResponse

fun LaunchResponse.toLaunch(): Launch = Launch(
    name,
    dateUtc,
    success
)