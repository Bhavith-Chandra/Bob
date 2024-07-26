package com.example.bob

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bob.model.*
import com.example.bob.util.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var statusTextView: TextView
    private lateinit var rideRecyclerView: RecyclerView
    private val rideViewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusTextView = findViewById(R.id.statusTextView)
        rideRecyclerView = findViewById(R.id.rideRecyclerView)
        rideRecyclerView.layoutManager = LinearLayoutManager(this)

        rideViewModel.rideInfoList.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    statusTextView.text = "Rides Loaded"
                    rideRecyclerView.adapter = RideAdapter(result.data)
                }
                is Result.Error -> {
                    statusTextView.text = "Error loading rides: ${result.exception.message}"
                }
                Result.Loading -> {
                    statusTextView.text = "Loading..."
                }
            }
        })

        fetchRidePrices()
    }

    private fun fetchRidePrices() {
        rideViewModel.fetchRidePrices()
    }
}
