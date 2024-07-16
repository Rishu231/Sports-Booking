package com.example.sportsbooking

import com.example.sportsbooking.network.ApiClient
import com.example.sportsbooking.mainData.view.IMainPresenter
import com.example.sportsbooking.mainData.view.IMainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private var view: IMainView) : IMainPresenter {

    override fun loadSports() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getVenueActivities()
                if (response.success) {
                    withContext(Dispatchers.Main) {
                        view.showSports(response.response)
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
