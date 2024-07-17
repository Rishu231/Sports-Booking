package com.example.sportsbooking.model
import com.google.gson.annotations.SerializedName

data class OrderDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("turf_id") val turf_id: Int,
    @SerializedName("turf_name") val turfName: String,
    @SerializedName("category_id") val category_id: Int,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("date") val date: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("venture_id") val venture_id: Int,
    @SerializedName("venture_name") val ventureName: String,
    @SerializedName("venture_icon") val venture_icon: String,
    @SerializedName("type_name") val typeName: String,
    @SerializedName("status") val status: String
)