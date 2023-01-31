package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.FavoriteRecipeId
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.data.RecipeWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT recipes.id, recipes.category_id, recipes.title, recipes.image " +
            "FROM recipes WHERE recipes.id IN (SELECT id FROM favorite_recipe_ids)")
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>

    @Query("SELECT favorite_recipe_ids.recipe_id FROM favorite_recipe_ids")
    fun getFavoriteRecipeIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteRecipe(recipeId: FavoriteRecipeId, recipe: Recipe, ingredients: List<RecipeIngredient>)

    @Query("delete from favorite_recipe_ids where recipe_id = :recipeId")
    suspend fun deleteFavoriteRecipe(recipeId: Int)

    @Update
    suspend fun updateFavoriteRecipe(recipeId: FavoriteRecipeId)

    @Query("delete from favorite_recipe_ids")
    suspend fun deleteAllFavoriteRecipes()
}
