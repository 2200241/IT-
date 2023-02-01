package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeUseCase {
    suspend fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<RecipeIngredient>>>
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>)
    suspend fun deleteRecipe(id: Int)
}

@Singleton
class RecipeUseCaseImpl @Inject constructor(
    private val recipeRepository: RecipeRepository
): RecipeUseCase {

    override suspend fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<RecipeIngredient>>> = recipeRepository.getRecipeDetail(id)

    override suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>) {
        recipeRepository.addRecipe(recipe, ingredients, instructions)
    }

    override suspend fun deleteRecipe(id: Int) = recipeRepository.deleteRecipe(id)
}

