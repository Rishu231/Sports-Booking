package com.example.sportsbooking.turfDetails.view

interface ITurfPresenter {
    fun loadTurfDetails(turfId: Int)
    fun loadTurfSlots(turfId: Int, date: String)
}
