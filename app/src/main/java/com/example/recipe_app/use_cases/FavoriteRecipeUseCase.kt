package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
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
            success = { favoriteRecipeRepository.addFavoriteRecipe(
//                Recipe(it.id, it.categoryId, it.title, it.image, it.servings),
                RecipeDetail(
                    Recipe(it.id, it.categoryId, it.title, it.image, it.servings),
                    it.ingredients.map { ing -> RecipeIngredient(0, it.id, ing.name, ing.quantity) },
                    it.instructions.mapIndexed { index, ins -> Instruction(0, it.id, index, ins) }
                )
            ) },
            failure = { }
        )
    }

    override suspend fun deleteFavoriteRecipe(recipeId: Int) = favoriteRecipeRepository.deleteFavoriteRecipe(recipeId)
}

