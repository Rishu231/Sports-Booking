package com.example.sportsbooking.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.sportsbooking.R
import com.example.sportsbooking.booking.view.IBookView
import com.example.sportsbooking.model.OrderDetails
import com.example.sportsbooking.model.ResponseData
import com.example.sportsbooking.model.Slots
import com.example.sportsbooking.model.SlotRequestData
import com.google.gson.Gson

class BookingActivity : AppCompatActivity(), IBookView {
    private lateinit var presenter: BookingPresenter

    private var turf_id: Int = 0
    private var start_time: String = ""
    private var end_time: String = ""
    private var order_id: String = ""
    private lateinit var btn: Button
    private lateinit var booked: Button
    private lateinit var see_order: Button
    private lateinit var order_details_main: LinearLayout
    private lateinit var first_input_txt: EditText
    private lateinit var last_input_txt: EditText
    private lateinit var email_input_txt: EditText
    private lateinit var dial_code_txt: EditText
    private lateinit var dial_number_txt: EditText

    //order detail description
    private lateinit var turf_name: TextView
    private lateinit var category_name: TextView
    private lateinit var date: TextView
    private lateinit var start_time_txt: TextView
    private lateinit var end_time_txt: TextView
    private lateinit var duration: TextView
    private lateinit var venture_name: TextView
    private lateinit var venture_icon: TextView
    private lateinit var type_name: TextView
    private lateinit var status: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        btn = findViewById(R.id.submit)
        booked = findViewById(R.id.booked)
        see_order = findViewById(R.id.see_order)
        order_details_main = findViewById(R.id.order_details_main)
        first_input_txt = findViewById(R.id.first_input_txt)
        last_input_txt = findViewById(R.id.last_input_txt)
        email_input_txt = findViewById(R.id.email_input_txt)
        dial_code_txt = findViewById(R.id.dial_code_txt)
        dial_number_txt = findViewById(R.id.dial_number_txt)

        //order detail description
        turf_name = findViewById(R.id.turf_name)
        category_name = findViewById(R.id.category_name)
        date = findViewById(R.id.date)
        start_time_txt = findViewById(R.id.start_time)
        end_time_txt = findViewById(R.id.end_time)
        duration = findViewById(R.id.duration)
        venture_name = findViewById(R.id.venture_name)
        type_name = findViewById(R.id.type_name)
        status = findViewById(R.id.status)


        turf_id = intent.getIntExtra("id", 2)
        start_time = intent.getStringExtra("start_time") ?: ""
        end_time = intent.getStringExtra("end_time") ?: ""
        presenter = BookingPresenter(this)


        btn.setOnClickListener {
            submitData()
        }

        see_order.setOnClickListener {
            order_details_main.visibility = View.VISIBLE
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun turfBooked(response: ResponseData) {
        if (!response.order_id.isNullOrEmpty()){
            btn.visibility = View.GONE
            booked.visibility = View.VISIBLE
            see_order.visibility = View.VISIBLE
            order_id = response.order_id
            presenter.showOrderDetails(order_id)
        }
        Toast.makeText(this, "Turf booked successfully! Order ID: ${response.order_id}", Toast.LENGTH_SHORT).show()
    }

    override fun orderDetails(response: List<OrderDetails>) {
        turf_name.text = response[0].turfName
        category_name.text = response[0].categoryName
        date.text = response[0].date
        start_time_txt.text = response[0].startTime
        end_time_txt.text = response[0].endTime
        duration.text = response[0].duration.toString()
        venture_name.text = response[0].ventureName
        type_name.text = response[0].typeName
        status.text = response[0].status
//        Toast.makeText(this, "su12345678", Toast.LENGTH_SHORT).show()
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
//            user_first_name = "abcv",
//            user_last_name = "abvc",
//            user_email = "apvx@gmail.com",
//            user_dial_code = "+91",
//            user_mobile_no = "6789543201"
            user_first_name = first_input_txt.text.toString(),
            user_last_name = last_input_txt.text.toString(),
            user_email = email_input_txt.text.toString(),
            user_dial_code = dial_code_txt.text.toString(),
            user_mobile_no = dial_number_txt.text.toString()
        )
        presenter.BookTurf(requestData)

    }
}