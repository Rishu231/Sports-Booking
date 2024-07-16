package com.example.sportsbooking.venue

import com.example.sportsbooking.network.ApiClient
import com.example.sportsbooking.venue.view.IVenueView
import com.example.sportsbooking.venue.view.IVenuePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VenuePresenter(private var view: IVenueView) : IVenuePresenter {

    override fun loadVenues(ventureId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getTurfs(ventureId)
                if (response.success) {
                    withContext(Dispatchers.Main) {
                        view.showTurfs(response.response)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        view.showError(response.message)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError(e.message ?: "Error fetching venues")
                }
            }
        }
    }
}
