package com.example.recipe_app.room.favorite_recipe

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM FavoriteRecipes")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteRecipe(favorite: FavoriteRecipe)

    @Query("delete from FavoriteRecipes where id = :id")
    suspend fun deleteFavoriteRecipe(id: Int)

    @Update
    suspend fun updateFavoriteRecipe(favoriteRecipes: FavoriteRecipe)

    @Query("delete from FavoriteRecipes")
    suspend fun deleteAllFavoriteRecipes()
}
