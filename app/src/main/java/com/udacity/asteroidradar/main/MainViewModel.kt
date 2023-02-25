package com.udacity.asteroidradar.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.NeoFeedResponse
import com.udacity.asteroidradar.api.TodayImageResponse
import com.udacity.asteroidradar.repository.NeoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder

class MainViewModel : ViewModel() {
    val repo= NeoRepository()
    private val _mutableAsteroidsList = MutableLiveData<List<com.udacity.asteroidradar.api.Asteroid>>()
    val objects: LiveData<List<com.udacity.asteroidradar.api.Asteroid>> =_mutableAsteroidsList
    private val _mutabletodayImage= MutableLiveData<TodayImageResponse>()
    val todayImage:LiveData<TodayImageResponse> =_mutabletodayImage

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter()
    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate = LocalDate.now().format(formatter)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getWeeksAsteroid() {
        viewModelScope.launch {
            val response = repo.getNeoFeed(currentDate.toString(),"2023-03-03")
            _mutableAsteroidsList.value = response?.near_earth_objects?.get("2023-02-25")
        }

    }
    suspend fun getTodayImage() {
        viewModelScope.launch {
            val response = repo.getTodayImage()
            _mutabletodayImage.value = response
        }

    }


}