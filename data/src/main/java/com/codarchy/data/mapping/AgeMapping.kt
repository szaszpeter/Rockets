package com.codarchy.data.mapping

import com.codarchy.data.model.AgeEstimation
import com.codarchy.data.network.AgeResponse

fun AgeResponse.toAgeEstimation(): AgeEstimation = AgeEstimation(name, age, count)