package com.example.sportsbooking.booking.view

import com.example.sportsbooking.model.BookingResponse

interface IBookView {
    fun showError(s: String)
    fun turfBooked(response: BookingResponse)
}
