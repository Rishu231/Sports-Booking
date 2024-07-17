package com.example.sportsbooking.network

import com.example.sportsbooking.model.ApiResponse
import com.example.sportsbooking.model.BookingResponse
import com.example.sportsbooking.model.OrderDetails
import com.example.sportsbooking.model.Slotdata
import com.example.sportsbooking.model.SlotRequestData
import com.example.sportsbooking.model.SportsModel
import com.example.sportsbooking.model.TurfDetail
import com.example.sportsbooking.model.VenueModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @GET("/vendor/venue/v1/activities")
    suspend fun getVenueActivities(): ApiResponse<List<SportsModel>>

    @GET("vendor/venue/v1/turfs")
    suspend fun getTurfs(@Query("venture_id") ventureId: Int): ApiResponse<List<VenueModel>>

    @GET("/vendor/venue/v1/turfdetails")
    suspend fun getTurfsDetail(@Query("turf_id") turf_id: Int): ApiResponse<List<TurfDetail>>

    @GET("/vendor/venue/v1/slots")
    suspend fun getTurfsAvailableSlot(@Query("turf_id") turf_id: Int, @Query("date") date: String): ApiResponse<List<Slotdata>>

    @POST("/vendor/venue/v1/bookslot")
    suspend fun bookSlot(@Body slots: SlotRequestData): BookingResponse

    @GET("/vendor/venue/v1/orderdetails")
    suspend fun getOrderDetails(@Query("order_id") order_id: String): ApiResponse<List<OrderDetails>>



//    @GET("/vendor/venue/v1/slots")
//    suspend fun getVenueSlots(): List<Slot>
//
//    @POST("/vendor/venue/v1/validateslot")
//    suspend fun validateSlot(@Body slotId: Int): Response<Unit>
//
//    @POST("/vendor/venue/v1/bookslot")
//    suspend fun bookSlot(@Body slotId: Int): Response<Unit>
//
//    @GET("/vendor/venue/v1/orderdetails")
//    suspend fun getOrderDetails(): Response<OrderDetails>
//
//    @POST("/vendor/venue/v1/cancelbooking")
//    suspend fun cancelBooking(@Body bookingId: Int): Response<Unit>
}
