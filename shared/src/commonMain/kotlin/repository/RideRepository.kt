package com.example.bob.repository

import kotlinx.coroutines.*
import com.example.bob.model.*
import com.example.bob.network.*
import com.example.bob.util.*

object RideRepository {
    private const val OLA_API_URL = "https://api.ola.com/price"
    private const val UBER_API_URL = "https://api.uber.com/price"

    suspend fun getRideComparisons(): List<Result<RideInfo>> = coroutineScope {
        val olaDeferred = async { RideApiClient.getRidePrice(OLA_API_URL) }
        val uberDeferred = async { RideApiClient.getRidePrice(UBER_API_URL) }
        listOf(olaDeferred.await(), uberDeferred.await())
    }
}
