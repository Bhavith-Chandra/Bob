package com.example.bob

import androidx.lifecycle.*
import kotlinx.coroutines.*
import com.example.bob.model.*
import com.example.bob.repository.*
import com.example.bob.util.*

class RideViewModel : ViewModel() {
    private val _rideInfoList = MutableLiveData<Result<List<RideInfo>>>()
    val rideInfoList: LiveData<Result<List<RideInfo>>> get() = _rideInfoList

    fun fetchRidePrices() {
        _rideInfoList.value = Result.Loading
        viewModelScope.launch {
            val result = RideRepository.getRideComparisons()
            _rideInfoList.value = Result.Success(result.filterIsInstance<Result.Success<RideInfo>>().map { it.data })
        }
    }
}
