package com.example.sportsbooking.model

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val response: ResponseData,
    val metadata: List<Any>,
    val requestLocation: String
)

data class ResponseData(
    val order_id: String
)
