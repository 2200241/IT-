package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.RecipeWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT recipes.id, recipes.category_id, recipes.title, recipes.image " +
            "FROM recipes WHERE recipes.id IN (SELECT id FROM favorite_recipe_ids)")
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteRecipe(id: Int)

    @Query("delete from favorite_recipe_ids where id = :id")
    suspend fun deleteFavoriteRecipe(id: Int)

    @Update
    suspend fun updateFavoriteRecipe(id: Int)

    @Query("delete from favorite_recipe_ids")
    suspend fun deleteAllFavoriteRecipes()
}
