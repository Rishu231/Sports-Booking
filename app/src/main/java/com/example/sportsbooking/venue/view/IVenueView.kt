package com.example.sportsbooking.venue.view

import com.example.sportsbooking.model.VenueModel

interface IVenueView {
    fun showError(s: String)
    fun showTurfs(it: List<VenueModel>)
}
