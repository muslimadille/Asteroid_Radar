package com.udacity.asteroidradar.repository
import android.os.Build
import androidx.annotation.RequiresApi
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.ApiClient
import com.udacity.asteroidradar.api.NeoFeedResponse
import com.udacity.asteroidradar.api.TodayImageResponse

class NeoRepository (){
    private val apiClient= ApiClient()
    private val apiService=apiClient.getApiService()


    @RequiresApi(Build.VERSION_CODES.N)
    suspend fun getNeoFeed(startDate: String, endDate: String): NeoFeedResponse? {
        val response=try {
            apiService.getNeoFeed(startDate, endDate, BuildConfig.API_KEY)
        } catch (e: Exception) {
            null
        }

        if (response!=null){
            response.near_earth_objects?.forEach{ (date, asteroids) ->
                asteroids.forEach { item->
                    item.date=date
                    item.relative_velocity= item.closeApproachData?.get(0)?.relative_velocity?.kilometers_per_second
                    item.miss_distance=item.closeApproachData?.get(0)?.missDistance?.kilometers.toString()
                }
            }
        }


        return response
    }
    suspend fun getTodayImage(): TodayImageResponse? {
        val response=try {
            apiService.getTodayImage(BuildConfig.API_KEY)
        } catch (e: Exception) {
            null
        }
        return response
    }

}