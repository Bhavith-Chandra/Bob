package com.example.bob.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RideInfo(
    val service: String,
    val price: Double,
    val eta: Int // Estimated Time of Arrival in minutes
)

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val status: String,
    val data: T
)
