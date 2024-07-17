package com.example.sportsbooking.booking.view

import com.example.sportsbooking.model.ResponseData

interface IBookView {
    fun showError(s: String)
    fun turfBooked(response: ResponseData)
}
