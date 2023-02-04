package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.repositories.RecipeRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoriteRecipeUseCase {
    fun getFavoriteRecipes(): Flow<List<RecipeWithCategory>>
    fun getFavoriteRecipeIds(): Flow<List<Int>>
    suspend fun addFavoriteRecipe(id: Int): Result<String, String>
    suspend fun deleteFavoriteRecipe(recipeId: Int): Result<String, String>
}

class FavoriteRecipeUseCaseImpl @Inject constructor(
    private val favoriteRecipeRepository: FavoriteRecipeRepository,
    private val recipeRepository: RecipeRepository,
    private val apiRepository: ApiRepository
): FavoriteRecipeUseCase {
    override fun getFavoriteRecipes() = favoriteRecipeRepository.getAllFavoriteRecipes()
    override fun getFavoriteRecipeIds() = favoriteRecipeRepository.getFavoriteRecipeIds()

    override suspend fun addFavoriteRecipe(id: Int): Result<String, String> {
        try {
            apiRepository.fetchRecipeById(id).mapBoth(
                success = {
                    favoriteRecipeRepository.addFavoriteRecipe(
                        DetailedRecipe(
                            Recipe(it.id, it.categoryId, it.title, it.image, it.servings),
                            it.ingredients.map { ing -> RecipeIngredient(0, it.id, ing.name, ing.quantity) },
                            it.instructions.mapIndexed { index, ins -> Instruction(0, it.id, index, ins) }
                        )
                    )
                    return Ok("Success")
                },
                failure = { return Err(it) }
            )
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    override suspend fun deleteFavoriteRecipe(recipeId: Int): Result<String, String> {
        return try {
            favoriteRecipeRepository.deleteFavoriteRecipe(recipeId)
            // 別枠でcatch
            try {
                recipeRepository.deleteRecipe(recipeId)
            } catch (e: Exception) {
            }
            Ok("Success")
        } catch (e: Exception) {
            Err(e.toString())
        }
    }
}

