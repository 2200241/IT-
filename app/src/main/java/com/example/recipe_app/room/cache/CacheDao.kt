package com.example.recipe_app.room.cache

import androidx.room.*

@Dao
interface CacheDao {
    @Query("SELECT * FROM caches")
    suspend fun getAllCaches(): List<Cache>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addCache(cache: Cache)

    @Query("delete from allergies")
    suspend fun deleteAllCaches()

}
