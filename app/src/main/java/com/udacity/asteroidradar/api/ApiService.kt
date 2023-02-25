package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
   @GET("neo/rest/v1/feed")
   suspend fun getNeoFeed(
      @Query("start_date") startDate: String,
      @Query("end_date") endDate: String,
      @Query("api_key") apiKey: String
   ): NeoFeedResponse

   @GET("planetary/apod")
   suspend fun getTodayImage(
      @Query("api_key") apiKey: String
   ): TodayImageResponse

   }