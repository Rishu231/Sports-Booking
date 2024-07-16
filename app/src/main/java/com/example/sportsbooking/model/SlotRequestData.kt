package com.example.sportsbooking.model

import com.google.gson.annotations.SerializedName

data class Slots(
    val turf_id: Int,
    val start_time: String,
    val end_time: String
)

data class SlotRequestData(
     val slots: List<Slots>,
     val user_first_name: String,
     val user_last_name: String,
     val user_email: String,
     val user_dial_code: String,
     val user_mobile_no: String
)