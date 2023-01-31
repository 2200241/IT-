package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailDao {

    @Query("SELECT recipes.id, recipes.category_id, recipes.title, recipes.image, recipes.servings, recipes.instructions, recipe_ingredients.name, recipe_ingredients.quantity " +
            "FROM recipes JOIN recipe_ingredients ON recipe_ingredients.recipe_id = recipes.id WHERE recipes.id = :id")
    fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<Ingredient>>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>)

    @Query("delete from recipes where id = :id")
    suspend fun deleteRecipe(id: Int)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("delete from recipes")
    suspend fun deleteAllRecipes()

}