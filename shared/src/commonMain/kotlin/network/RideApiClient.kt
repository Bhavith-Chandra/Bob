package com.example.bob.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.example.bob.model.*
import com.example.bob.util.*

object RideApiClient {
    private val client = HttpClient()
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val rideInfoAdapter: JsonAdapter<ApiResponse<RideInfo>> = moshi.adapter(ApiResponse::class.java)

    suspend fun getRidePrice(serviceUrl: String): Result<RideInfo> {
        return try {
            val response: HttpResponse = client.get(serviceUrl)
            if (response.status == HttpStatusCode.OK) {
                val responseBody = response.readText()
                val apiResponse = rideInfoAdapter.fromJson(responseBody)
                if (apiResponse?.status == "success" && apiResponse.data != null) {
                    Result.Success(apiResponse.data)
                } else {
                    Result.Error(Exception("Failed to fetch ride information"))
                }
            } else {
                Result.Error(Exception("HTTP error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
