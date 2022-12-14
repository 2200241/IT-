package com.example.recipe_app.room.favorite

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorites")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addFavorite(favorite: Favorite)

    @Query("delete from favorites where favorite_id = :favorite_id")
    suspend fun deleteFavorite(favorite_id: Int)

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Query("delete from favorites")
    suspend fun deleteAllFavorites()
}
