package com.example.recipe_app.use_cases.favorite_recipe

import com.example.recipe_app.repositories.FavoriteRecipeRepository
import javax.inject.Inject

class DeleteFavoriteRecipeUseCase @Inject constructor(
    private val repository: FavoriteRecipeRepository
) {
    suspend fun deleteFavorite(id: Int) {

        repository.deleteFavoriteRecipe(id)
    }
}
