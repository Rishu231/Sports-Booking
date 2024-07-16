package com.example.sportsbooking.venue.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.R
import com.example.sportsbooking.model.TurfModel
import com.example.sportsbooking.model.VenueModel
import com.example.sportsbooking.turfDetails.TurfDetailActivity
import com.example.sportsbooking.venue.VenueActivity

class TurfAdapter (private var context: VenueActivity): RecyclerView.Adapter<TurfAdapter.VenueViewHolder>() {

    private var turf: List<TurfModel> = listOf()
    private var venues: List<VenueModel> = listOf()

    fun setTurf(turf: List<TurfModel>, venues: List<VenueModel>, context: VenueActivity) {
        this.turf = turf
        this.context = context
        this.venues = venues

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_turf, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val turf = turf[position]
        holder.bind(turf,venues, context)
    }

    override fun getItemCount(): Int = turf.size

    class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainll: LinearLayout = itemView.findViewById(R.id.turf_mainll)
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
//        val id: TextView = itemView.findViewById(R.id.id)

        fun bind(turf: TurfModel, venues: List<VenueModel>, context: VenueActivity) {
            tvName.text = turf.name
//            id.text = venue.venture_id.toString()
//            Picasso.get()
//                .load("http://43.205.87.112:8080/venture_icons/${venues[0].icon}")
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(R.drawable.ic_launcher_background)
//                .into(ivIcon)

            mainll.setOnClickListener {
                val intent = Intent(context, TurfDetailActivity::class.java)
                intent.putExtra("id", turf.id)
                context.startActivity(intent)
            }
        }
    }

}
