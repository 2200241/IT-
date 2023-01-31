package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.data.RecipeWithCategory
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoriteRecipeUseCase {
    fun getFavoriteRecipes(): Flow<List<RecipeWithCategory>>
    fun getFavoriteRecipeIds(): Flow<List<Int>>
    suspend fun addFavoriteRecipe(id: Int)
    suspend fun deleteFavoriteRecipe(recipeId: Int)
}

class FavoriteRecipeUseCaseImpl @Inject constructor(
    private val favoriteRecipeRepository: FavoriteRecipeRepository,
    private val apiRepository: ApiRepository
): FavoriteRecipeUseCase {
    override fun getFavoriteRecipes() = favoriteRecipeRepository.getAllFavoriteRecipes()
    override fun getFavoriteRecipeIds() = favoriteRecipeRepository.getFavoriteRecipeIds()

    override suspend fun addFavoriteRecipe(id: Int) {
        apiRepository.fetchRecipeById(id).mapBoth(
            success = { it.map { entry -> favoriteRecipeRepository.addFavoriteRecipe(
                entry.key,
                entry.value.map { ing -> RecipeIngredient(id = 0, recipeId = entry.key.id, name = ing.name, quantity = ing.quantity) }
            ) } },
            failure = { }
        )
    }

    override suspend fun deleteFavoriteRecipe(recipeId: Int) = favoriteRecipeRepository.deleteFavoriteRecipe(recipeId)
}

