package com.example.sportsbooking.booking

import android.util.Log
import com.example.sportsbooking.booking.view.IBookPresenter
import com.example.sportsbooking.booking.view.IBookView
import com.example.sportsbooking.model.SlotRequestData
import com.example.sportsbooking.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookingPresenter(private var view: IBookView) : IBookPresenter {

    override fun BookTurf(dat: SlotRequestData) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.bookSlot(dat)
                if (response.success) {
                    withContext(Dispatchers.Main) {
                        view.turfBooked(response.response)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        view.showError(response.error)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError(e.cause?.message ?: "Error fetching venues")
                }
            }
        }
    }

    override fun showOrderDetails(orderId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getOrderDetails(orderId)
                if (response.success) {
                    withContext(Dispatchers.Main) {
                        view.orderDetails(response.response)
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
