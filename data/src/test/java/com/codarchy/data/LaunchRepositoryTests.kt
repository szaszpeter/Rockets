package com.codarchy.data

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Launch
import com.codarchy.data.model.LaunchQuery
import com.codarchy.data.model.Query
import com.codarchy.data.model.Rocket
import com.codarchy.data.network.LaunchResponseWrapper
import com.codarchy.data.network.RocketApi
import com.codarchy.data.network.RocketResponse
import com.codarchy.data.repository.LaunchRepository
import com.codarchy.data.repository.RocketRepository
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LaunchRepositoryTests {

    lateinit var launchRepository: LaunchRepository

    @Mock
    lateinit var apiService: RocketApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        launchRepository = LaunchRepository(apiService)
    }

    @Test
    fun getRocketsTest() {
        runBlocking {
            Mockito.`when`(apiService.getLaunchesByRocketId(LaunchQuery(Query("rocketId"))))
                .thenReturn(
                    LaunchResponseWrapper(listOf())
                )
            val response = launchRepository.requestLaunchByRocketId("rocketId")
            assertEquals(ResultWrapper.Success(listOf<Launch>()), response)
        }
    }

    @Test
    fun getRocketsFailingTest() {
        runBlocking {
            Mockito.`when`(apiService.getLaunchesByRocketId(LaunchQuery(Query("rocketId"))))
                .thenThrow()
            val response = launchRepository.requestLaunchByRocketId("rocketId")
            assertEquals(ResultWrapper.GenericError(), response)
        }
    }
}