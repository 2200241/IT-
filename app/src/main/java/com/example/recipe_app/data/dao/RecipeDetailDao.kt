package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailDao {

    // TODO: タグ取得
    @Query("SELECT recipes.*, recipe_ingredients.*, instructions.* " +
            "FROM recipes " +
            "JOIN recipe_ingredients ON recipe_ingredients.recipe_id = recipes.id " +
            "JOIN instructions ON instructions.recipe_id = recipes.id " +
            "WHERE recipes.id = :id")
    fun getRecipeDetail(id: Int): DetailedRecipe

/*
    @Transaction
    @Query("SELECT * FROM recipes WHERE recipes.id = :id")
    suspend fun getRecipeDetail(id: Int): List<RecipeDetail>
*/

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>)

    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun deleteRecipe(id: Int)

    @Query("DELETE FROM recipes WHERE id IN (:ids)")
    suspend fun deleteRecipes(ids: List<Int>)

    @Query("DELETE FROM instructions WHERE recipe_id = :id")
    suspend fun deleteInstructions(id: Int)

    @Query("DELETE FROM recipe_ingredients WHERE recipe_id = :id")
    suspend fun deleteIngredients(id: Int)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()

}