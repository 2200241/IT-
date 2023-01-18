package com.example.recipe_app.room.favorite_menu

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMenuDao {

    @Query("SELECT * FROM FavoriteMenus")
    fun getAllFavoriteMenus(): Flow<List<FavoriteMenu>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteMenu(favoriteMenu: FavoriteMenu)

    @Query("delete from FavoriteMenus where id = :id")
    suspend fun deleteFavoriteMenu(id: Int)

    @Update
    suspend fun updateFavoriteMenu(favoriteMenu: FavoriteMenu)

    @Query("delete from FavoriteMenus")
    suspend fun deleteAllFavoriteMenus()
}