package com.example.sportsbooking.mainData.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.MainActivity
import com.example.sportsbooking.R
import com.example.sportsbooking.model.SportsModel
import com.squareup.picasso.Picasso

class SportsAdapter (private val context: MainActivity): RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {

    private var sports: List<SportsModel> = listOf()

    fun setSports(sports: List<SportsModel>) {
        this.sports = sports
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sports, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sports[position]
        holder.bind(sport, context)
    }

    override fun getItemCount(): Int = sports.size

    class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainll: LinearLayout = itemView.findViewById(R.id.mainll)
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bind(sports: SportsModel, context: MainActivity) {
            tvName.text = sports.name
            Picasso.get()
                .load("http://43.205.87.112:8080/venture_icons/"+sports.icon)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(ivIcon)

            mainll.setOnClickListener {
                context.sportsId(sports.venture_id)
            }
        }
    }

}
