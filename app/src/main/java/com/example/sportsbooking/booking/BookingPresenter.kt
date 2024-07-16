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
                        Log.e("1234566778", response.response.toString())
                        view.turfBooked(response.response)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("error1234566778", response.response.toString())
                        view.showError(response.message)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("error12345667782", e.message.toString())
                    view.showError(e.message ?: "Error fetching venues")
                }
            }
        }
    }

//    override fun loadTurfSlots(turfId: Int, date: String ) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = ApiClient.apiService.getTurfsAvailableSlot(turfId, date)
//                if (response.success) {
//                    withContext(Dispatchers.Main) {
//                        view.showSlots(response.response)
//                    }
//                } else {
//                    withContext(Dispatchers.Main) {
//                        view.showError(response.message)
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    view.showError(e.message ?: "Error fetching venues")
//                }
//            }
//        }
//    }
}
