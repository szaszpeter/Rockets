package com.codarchy.data

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.Rocket
import com.codarchy.data.network.RocketApi
import com.codarchy.data.network.RocketResponse
import com.codarchy.data.repository.RocketRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RocketRepositoryTests {

    lateinit var rocketRepository: RocketRepository

    @Mock
    lateinit var apiService: RocketApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        rocketRepository = RocketRepository(apiService)
    }

    @Test
    fun getRocketsTest() {
        runBlocking {
            Mockito.`when`(apiService.getRocketList()).thenReturn(listOf<RocketResponse>())
            val response = rocketRepository.requestRocketList()
            assertEquals(ResultWrapper.Success(listOf<Rocket>()), response)
        }
    }

    @Test
    fun getRocketsFailingTest() {
        runBlocking {
            Mockito.`when`(apiService.getRocketList()).thenThrow()
            val response = rocketRepository.requestRocketList()
            assertEquals(ResultWrapper.GenericError(), response)
        }
    }

}