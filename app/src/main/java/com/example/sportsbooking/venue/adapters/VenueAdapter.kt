package com.example.sportsbooking.venue.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.R
import com.example.sportsbooking.model.CategoryModel
import com.example.sportsbooking.model.VenueModel
import com.example.sportsbooking.venue.VenueActivity
import com.squareup.picasso.Picasso

class VenueAdapter(private val context: VenueActivity) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {

    private var venues: List<VenueModel> = listOf()
    private var category: List<CategoryModel> = listOf()

    fun setVenue(categories: List<CategoryModel>, venues: List<VenueModel>) {
        this.category = categories
        this.venues = venues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val category = category[position]
        holder.bind(category, venues, context)
    }

    override fun getItemCount(): Int = category.size

    class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainll: LinearLayout = itemView.findViewById(R.id.venue_mainll)
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val recycler: RecyclerView = itemView.findViewById(R.id.rv_turf)

        fun bind(categories: CategoryModel, venues: List<VenueModel>, context: VenueActivity) {
            tvName.text = categories.name
            Picasso.get()
                .load("http://43.205.87.112:8080/venture_icons/${venues[0].icon}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(ivIcon)

            val turfAdapter = TurfAdapter(context)
            recycler.layoutManager = LinearLayoutManager(itemView.context)
            recycler.adapter = turfAdapter

            mainll.setOnClickListener {
                recycler.visibility = if (recycler.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                turfAdapter.setTurf(categories.turfs, venues, context)
            }
        }
    }
}
