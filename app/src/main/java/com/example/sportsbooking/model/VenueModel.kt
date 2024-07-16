package com.example.sportsbooking.model

data class VenueModel(
    val venue_id: Int,
    val venture_id: Int,
    val name: String,
    val icon: String,
    val categories: List<CategoryModel>
)

data class CategoryModel(
    val id: Int,
    val name: String,
    val venue_property_description: String?,
    val turfs: List<TurfModel>
)

data class TurfModel(
    val id: Int,
    val name: String,
    val category_id: Int
)

