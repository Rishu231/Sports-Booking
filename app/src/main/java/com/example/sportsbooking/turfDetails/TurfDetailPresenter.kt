package com.example.sportsbooking.turfDetails

import com.example.sportsbooking.network.ApiClient
import com.example.sportsbooking.turfDetails.view.ITurfPresenter
import com.example.sportsbooking.turfDetails.view.ITurfView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TurfDetailPresenter(private var view: ITurfView) : ITurfPresenter {

    override fun loadTurfDetails(turfId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getTurfsDetail(turfId)
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

    override fun loadTurfSlots(turfId: Int, date: String ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getTurfsAvailableSlot(turfId, date)
                if (response.success) {
                    withContext(Dispatchers.Main) {
                        view.showSlots(response.response)
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
