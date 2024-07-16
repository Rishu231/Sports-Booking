package com.example.sportsbooking.mainData.view

import com.example.sportsbooking.model.SportsModel

interface IMainView {
    fun showSports(sports: List<SportsModel>)
    fun showError(s: String)
    fun sportsId(ventureId: Int)
}
