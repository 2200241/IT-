package com.example.recipe_app.room.recipe

import androidx.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addRecipe(recipe: Recipe)

    @Query("delete from recipes where id = :id")
    suspend fun deleteRecipe(id: Int)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("delete from Recipes")
    suspend fun deleteAllRecipes()

}