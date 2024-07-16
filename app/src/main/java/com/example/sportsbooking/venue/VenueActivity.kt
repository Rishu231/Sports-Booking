package com.example.sportsbooking.venue

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.R
import com.example.sportsbooking.model.VenueModel
import com.example.sportsbooking.venue.adapters.VenueAdapter
import com.example.sportsbooking.venue.view.IVenueView

class VenueActivity : AppCompatActivity(), IVenueView {
    private lateinit var presenter: VenuePresenter
    private lateinit var venues: RecyclerView
    private lateinit var venueAdapter: VenueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue)
        val venture_id = intent.getIntExtra("id", 2)
        presenter = VenuePresenter(this)
        presenter.loadVenues(venture_id)
        venues = findViewById(R.id.rv_venues)


        venues.layoutManager = LinearLayoutManager(this)
        venueAdapter = VenueAdapter(this)
        venues.adapter = venueAdapter
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showTurfs(it: List<VenueModel>) {
        venueAdapter.setVenue(it[0].categories, it)
//        Log.e("turfsdata", it.toString())
    }

}