package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailDao {

//    "instructions.`order`, instructions.content " +

    @Query("SELECT recipes.*, recipe_ingredients.* " +
            "FROM recipes JOIN recipe_ingredients ON recipe_ingredients.recipe_id = recipes.id " +
            "JOIN instructions ON instructions.recipe_id = recipes.id " +
            "WHERE recipes.id = :id")
    fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<RecipeIngredient>>>
/*
    @Transaction
    @Query("SELECT * FROM recipes WHERE recipes.id = :id")
    suspend fun getRecipeDetail(id: Int): List<RecipeDetail>
*/

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>)

    // TODO
    @Query("delete from recipes where id = :id")
    suspend fun deleteRecipe(id: Int)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("delete from recipes")
    suspend fun deleteAllRecipes()

}