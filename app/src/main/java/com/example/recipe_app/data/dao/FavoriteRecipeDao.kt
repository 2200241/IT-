package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT recipes.id, recipes.category_id, recipes.title, recipes.image " +
            "FROM recipes WHERE recipes.id IN (SELECT recipe_id FROM favorite_recipe_ids) " +
            "ORDER BY recipes.id"
    )
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>

    @Query("SELECT favorite_recipe_ids.recipe_id FROM favorite_recipe_ids")
    fun getFavoriteRecipeIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteRecipe(recipe: Recipe, recipeId: FavoriteRecipeId, ingredients: List<RecipeIngredient>, instructions: List<Instruction>)

    @Query("DELETE FROM favorite_recipe_ids WHERE recipe_id = :recipeId")
    suspend fun deleteFavoriteRecipe(recipeId: Int)

    @Update
    suspend fun updateFavoriteRecipe(recipeId: FavoriteRecipeId)

    @Query("DELETE FROM favorite_recipe_ids")
    suspend fun deleteAllFavoriteRecipes()
}
