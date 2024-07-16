package com.example.sportsbooking.turfDetails.view

import com.example.sportsbooking.model.Slotdata
import com.example.sportsbooking.model.TurfDetail

interface ITurfView {
    fun showError(s: String)
    fun showTurfs(it: List<TurfDetail>)
    fun showSlots(response: List<Slotdata>)
}
