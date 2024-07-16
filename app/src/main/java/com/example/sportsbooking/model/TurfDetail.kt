package com.example.sportsbooking.model
import com.google.gson.annotations.SerializedName

data class TurfDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("turf_name") val turfName: String,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("venture_name") val ventureName: String,
    @SerializedName("type_name") val typeName: String,
    @SerializedName("venue_property_description") val venuePropertyDescription: String?
)