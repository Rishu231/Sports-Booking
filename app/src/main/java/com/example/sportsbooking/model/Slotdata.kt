package com.example.sportsbooking.model

import com.google.gson.annotations.SerializedName

data class Slotdata(
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("base_price") val basePrice: String,
    @SerializedName("available") val available: Boolean
)
