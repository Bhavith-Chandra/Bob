package com.example.bob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bob.model.*

class RideAdapter(private val rideList: List<RideInfo>) : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceTextView: TextView = itemView.findViewById(R.id.serviceTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val etaTextView: TextView = itemView.findViewById(R.id.etaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = rideList[position]
        holder.serviceTextView.text = ride.service
        holder.priceTextView.text = "Price: ${ride.price}"
        holder.etaTextView.text = "ETA: ${ride.eta} mins"
    }

    override fun getItemCount(): Int {
        return rideList.size
    }
}
