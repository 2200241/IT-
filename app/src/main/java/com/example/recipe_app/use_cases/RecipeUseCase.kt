package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.RecipeRepository
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeUseCase {
    suspend fun getRecipeDetail(id: Int): Result<DetailedRecipe, String>
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>): Result<String, String>
    suspend fun deleteRecipe(id: Int): Result<String, String>
    suspend fun deleteRecipes(ids: List<Int>): Result<String, String>
}

@Singleton
class RecipeUseCaseImpl @Inject constructor(
    private val recipeRepository: RecipeRepository
): RecipeUseCase {

    override suspend fun getRecipeDetail(id: Int): Result<DetailedRecipe, String> = recipeRepository.getRecipeDetail(id)

    override suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>): Result<String, String> = recipeRepository.addRecipe(recipe, ingredients, instructions)

    override suspend fun deleteRecipe(id: Int) = recipeRepository.deleteRecipe(id)

    override suspend fun deleteRecipes(ids: List<Int>): Result<String, String> = recipeRepository.deleteRecipes(ids)
}

