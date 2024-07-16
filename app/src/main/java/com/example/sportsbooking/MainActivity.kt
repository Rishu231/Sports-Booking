package com.example.sportsbooking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.mainData.adapters.SportsAdapter
import com.example.sportsbooking.model.SportsModel
import com.example.sportsbooking.mainData.view.IMainView
import com.example.sportsbooking.venue.VenueActivity

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var presenter: MainPresenter
    private lateinit var sports: RecyclerView

    private lateinit var sportsAdapter: SportsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sports = findViewById(R.id.rv_sports)

        sports.layoutManager = LinearLayoutManager(this)
        sportsAdapter = SportsAdapter(this)
        sports.adapter = sportsAdapter

        presenter = MainPresenter(this)
        presenter.loadSports()
    }

    override fun showSports(venues: List<SportsModel>) {
        sportsAdapter.setSports(venues)
    }

    override fun showError(message: String) {
        Log.e("1234abc", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun sportsId(ventureId: Int) {
        val intent = Intent(this, VenueActivity::class.java)
        intent.putExtra("id", ventureId)
        startActivity(intent)
//        finish()
    }

}
