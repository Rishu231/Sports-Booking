package com.example.sportsbooking.booking.view

import com.example.sportsbooking.model.OrderDetails
import com.example.sportsbooking.model.ResponseData

interface IBookView {
    fun showError(s: String)
    fun turfBooked(response: ResponseData)
    fun orderDetails(response: List<OrderDetails>)
}
