package com.example.recipe_app.Room.Favorite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipe_app.Room.Favorite.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun readAllData(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addFavorite(favorite: Favorite)

    @Query("delete from favorites where favorite_id = :favorite_id")
    suspend fun deleteFavorite(favorite_id: Int)

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Query("delete from favorites")
    suspend fun deleteAllFavorites()

}