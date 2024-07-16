package com.example.sportsbooking.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sportsbooking.R
import com.example.sportsbooking.booking.view.IBookView
import com.example.sportsbooking.model.BookingResponse
import com.example.sportsbooking.model.Slots
import com.example.sportsbooking.model.SlotRequestData
import com.google.gson.Gson

class BookingActivity : AppCompatActivity(), IBookView {
    private lateinit var presenter: BookingPresenter

    private var turf_id: Int = 0
    private var start_time: String = ""
    private var end_time: String = ""
    private lateinit var btn: Button
    private lateinit var first_input_txt: EditText
    private lateinit var last_input_txt: EditText
    private lateinit var email_input_txt: EditText
    private lateinit var dial_code_txt: EditText
    private lateinit var dial_number_txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        btn = findViewById(R.id.submit)
        first_input_txt = findViewById(R.id.first_input_txt)
        last_input_txt = findViewById(R.id.last_input_txt)
        email_input_txt = findViewById(R.id.email_input_txt)
        dial_code_txt = findViewById(R.id.dial_code_txt)
        dial_number_txt = findViewById(R.id.dial_number_txt)


        turf_id = intent.getIntExtra("id", 2)
        start_time = intent.getStringExtra("start_time") ?: ""
        end_time = intent.getStringExtra("end_time") ?: ""
        presenter = BookingPresenter(this)


        btn.setOnClickListener {
            submitData()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun turfBooked(response: BookingResponse) {
//        Log.e("qwertyuiop----", response.toString())
        Toast.makeText(this, "Turf booked successfully! Order ID: ${response.response.order_id}", Toast.LENGTH_SHORT).show()

    }

    fun submitData(){
        val slots = listOf(
            Slots(
                turf_id = turf_id,
                start_time = start_time,
                end_time = end_time
            )
        )
//        Toast.makeText(this, slots.toString(), Toast.LENGTH_SHORT).show()
        val requestData = SlotRequestData(
            slots = slots,
            user_first_name = first_input_txt.text.toString(),
            user_last_name = last_input_txt.text.toString(),
            user_email = email_input_txt.text.toString(),
            user_dial_code = dial_code_txt.text.toString(),
            user_mobile_no = dial_number_txt.text.toString()
        )
        Log.e("qwertyuiop----", requestData.toString())
        val gson = Gson()
        Log.d("SlotRequestData", gson.toJson(requestData))
        presenter.BookTurf(requestData)

    }
}