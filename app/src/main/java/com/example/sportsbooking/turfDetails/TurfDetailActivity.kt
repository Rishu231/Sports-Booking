package com.example.sportsbooking.turfDetails

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.R
import com.example.sportsbooking.model.Slotdata
import com.example.sportsbooking.model.TurfDetail
import com.example.sportsbooking.turfDetails.adapters.TurfSlotAdapter
import com.example.sportsbooking.turfDetails.view.ITurfView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TurfDetailActivity : AppCompatActivity(), ITurfView {
    private lateinit var presenter: TurfDetailPresenter
    private lateinit var slotrecycler: RecyclerView
    private lateinit var slotAdapter: TurfSlotAdapter

    private lateinit var mainlayout: LinearLayout
    private lateinit var Name: TextView
    private lateinit var category_name: TextView
    private lateinit var venture_name: TextView
    private lateinit var type_name: TextView
    private lateinit var venue_description: TextView
    private var turf_id : Int = 0

    private lateinit var selectDateButton: Button
//    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turf_detail)
        val turf_id = intent.getIntExtra("id", 2)

        mainlayout = findViewById(R.id.available_slot_main)
        Name = findViewById(R.id.turf_name)
        category_name = findViewById(R.id.category_name)
        venture_name = findViewById(R.id.venture_name)
        type_name = findViewById(R.id.type_name)
        venue_description = findViewById(R.id.venue_description)

        selectDateButton = findViewById(R.id.selectDateButton)

        presenter = TurfDetailPresenter(this)
        presenter.loadTurfDetails(turf_id)

        slotrecycler = findViewById(R.id.rv_slot)
//        slotrecycler.layoutManager = LinearLayoutManager(this)
        slotrecycler.layoutManager = GridLayoutManager(this, 2)
        slotAdapter = TurfSlotAdapter(this)
        slotrecycler.adapter = slotAdapter

    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showTurfs(data: List<TurfDetail>) {
        turf_id = data[0].id
        Name.text = data[0].turfName
        category_name.text = data[0].categoryName
        venture_name.text = data[0].ventureName
        type_name.text = data[0].typeName
        venue_description.text = (if (data[0].venuePropertyDescription.isNullOrEmpty()){"Data not available!"} else {
            data[0].venuePropertyDescription
        }).toString()
//        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showSlots(response: List<Slotdata>) {
        mainlayout.visibility = View.VISIBLE
        slotAdapter.setTurf(response, turf_id, this)
//        Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
    }

    fun onSelectDateClicked(view: android.view.View) {
        val today = Calendar.getInstance()

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = formatDate(year, month, dayOfMonth)
                presenter.loadTurfSlots(turf_id, selectedDate)
//                Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = today.timeInMillis
        datePicker.show()
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}