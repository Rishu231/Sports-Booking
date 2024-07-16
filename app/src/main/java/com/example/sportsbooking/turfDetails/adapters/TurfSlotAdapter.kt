package com.example.sportsbooking.turfDetails.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsbooking.R
import com.example.sportsbooking.booking.BookingActivity
import com.example.sportsbooking.model.Slotdata
import com.example.sportsbooking.turfDetails.TurfDetailActivity

class TurfSlotAdapter (private var context: TurfDetailActivity): RecyclerView.Adapter<TurfSlotAdapter.VenueViewHolder>() {

    private var turf: List<Slotdata> = listOf()
    private var turf_id: Int = 0

    fun setTurf(turf: List<Slotdata>, turf_id: Int, context: TurfDetailActivity) {
        this.turf = turf
        this.turf_id = turf_id
        this.context = context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slot_detail, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val turf = turf[position]
        holder.bind(turf, turf_id, context)
    }

    override fun getItemCount(): Int = turf.size

    class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainll: LinearLayout = itemView.findViewById(R.id.slot_main)
        val start_time: TextView = itemView.findViewById(R.id.start_time)
        val end_time: TextView = itemView.findViewById(R.id.end_time)
        val price_txt: TextView = itemView.findViewById(R.id.price_txt)

        fun bind(slotdata: Slotdata, turf_id: Int, context: TurfDetailActivity) {
            start_time.text = slotdata.startTime
            end_time.text = slotdata.endTime
            price_txt.text = slotdata.basePrice
            if(slotdata.available){
                mainll.background = (ContextCompat.getDrawable(context, R.color.green_light))
            }else{
                mainll.background = (ContextCompat.getDrawable(context, R.color.red_medium))
            }
//            id.text = venue.venture_id.toString()
//            Picasso.get()
//                .load("http://43.205.87.112:8080/venture_icons/${venues[0].icon}")
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(R.drawable.ic_launcher_background)
//                .into(ivIcon)

            mainll.setOnClickListener {
                val intent = Intent(context, BookingActivity::class.java)
                intent.putExtra("id", turf_id)
                intent.putExtra("start_time", slotdata.startTime)
                intent.putExtra("end_time", slotdata.endTime)
                context.startActivity(intent)
            }
        }
    }

}
