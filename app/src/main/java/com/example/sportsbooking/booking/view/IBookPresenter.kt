package com.example.sportsbooking.booking.view

import com.example.sportsbooking.model.SlotRequestData

interface IBookPresenter {
    fun BookTurf(dat: SlotRequestData)
    fun showOrderDetails(orderId: String)
}
