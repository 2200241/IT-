package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeUseCase {
    fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<Ingredient>>>
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>)
    suspend fun deleteRecipe(id: Int)
}

@Singleton
class RecipeUseCaseImpl @Inject constructor(
    private val recipeRepository: RecipeRepository
): RecipeUseCase {

    override fun getRecipeDetail(id: Int) = recipeRepository.getRecipeDetail(id)

    override suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>) {
        recipeRepository.addRecipe(recipe, ingredients)
    }

    override suspend fun deleteRecipe(id: Int) = recipeRepository.deleteRecipe(id)
}

