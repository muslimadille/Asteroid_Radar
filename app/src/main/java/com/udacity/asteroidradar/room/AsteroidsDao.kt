package com.udacity.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.api.Asteroid

@Dao
interface AsteroidsDao {
   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Asteroid>)

    @Query("SELECT * FROM Asteroid")
    fun getAll(): LiveData<List<Asteroid>>*/
}